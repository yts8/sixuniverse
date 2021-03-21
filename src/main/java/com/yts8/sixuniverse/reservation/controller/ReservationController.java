package com.yts8.sixuniverse.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import com.yts8.sixuniverse.payment.dto.PaymentDto;
import com.yts8.sixuniverse.payment.service.PaymentService;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.service.ReservationService;
import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;
import com.yts8.sixuniverse.roomImage.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.DataInput;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
  private final ReservationService reservationService;
  private final RoomService roomService;
  private final MemberService memberService;
  private final ReservationDateService reservationDateService;
  private final RoomImageService roomImageService;
  private final PaymentService paymentService;

  @PostMapping("/{roomId}")
  public String reservation(HttpSession session, Model model,
                            ReservationDto reservationDto,
                            HttpServletRequest request,
                            @PathVariable Long roomId) {

    MemberDto memberDto = (MemberDto) session.getAttribute("member");
    RoomDto roomDto = roomService.findById(roomId);

    Long roomMemberId = roomDto.getMemberId();
    Long sessionMemberId = memberDto.getMemberId();

    LocalDate checkIn = reservationDto.getCheckIn();
    int days = Period.between(checkIn, reservationDto.getCheckOut()).getDays();

    List<LocalDate> reservationDateArray = new ArrayList<>();
    reservationDateArray.add(checkIn);
    for (int i = 1; i <= days; i++) {
      LocalDate reservationDate = checkIn.plusDays(i);
      reservationDateArray.add(reservationDate);
    }

    Collections.sort(reservationDateArray);

    if (sessionMemberId.equals(roomMemberId)) {

      return "redirect:/";
    } else {
      int commission = Integer.parseInt(request.getParameter("commission"));
      int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));

      List<LocalDate> reservationDateList = reservationDateService.reservationDateList(roomId);
      Collections.sort(reservationDateList);

      model.addAttribute("room", roomDto);
      model.addAttribute("beforeReservation", reservationDto);
      model.addAttribute("reservationDateArray", reservationDateArray);
      model.addAttribute("commission", commission);
      model.addAttribute("totalPrice", totalPrice);
      model.addAttribute("reservationDateList", reservationDateList);
      model.addAttribute("roomImages", roomImageService.findByRoomId(roomId));


      return "reservation/index";
    }


  }

  @GetMapping("/guest/list")
  public String guestReservation() {

    return "redirect:/reservation/guest/list/upcoming";
  }

  @GetMapping("/guest/list/{status}")
  public String guestReservation(HttpSession session, Model model, @PathVariable String status) {
    MemberDto memberDto = (MemberDto) session.getAttribute("member");
    Long memberId = memberDto.getMemberId();

    ReservationDto reservationDto = new ReservationDto();
    reservationDto.setMemberId(memberId);
    reservationDto.setStatus(status);

    List<ReservationDto> reservationList = reservationService.reservationList(reservationDto);

    List<RoomDto> roomList = new ArrayList<>();
    List<List<RoomImageDto>> roomImageList = new ArrayList<>();


    for (int i = 0; i < reservationList.size(); i++) {
      ReservationDto reservation = reservationList.get(i);

      Long roomId = reservation.getRoomId();

      RoomDto roomDto = roomService.findById(roomId);
      List<RoomImageDto> roomImageDto = roomImageService.findByRoomId(roomId);
      roomList.add(roomDto);
      roomImageList.add(roomImageDto);
    }

    model.addAttribute("status", status);
    model.addAttribute("roomList", roomList);
    model.addAttribute("roomImageList", roomImageList);
    model.addAttribute("reservationList", reservationList);

    return "reservation/guest/list";
  }

  @GetMapping("/guest/simple-info/{reservationId}")
  public String simple(Model model, @PathVariable Long reservationId) {
    ReservationDto reservationDto = reservationService.findById(reservationId);
    Long roomId = reservationDto.getRoomId();

    RoomDto roomDto = roomService.findById(reservationDto.getRoomId());
    MemberDto memberDto = memberService.findById(roomDto.getMemberId());
    List<RoomImageDto> roomImageDtoList = roomImageService.findByRoomId(roomId);

    model.addAttribute("reservation", reservationDto);
    model.addAttribute("room", roomDto);
    model.addAttribute("hostId", memberDto.getMemberId());
    model.addAttribute("roomImageDtoList", roomImageDtoList);

    return "reservation/guest/simple-info";
  }


  @GetMapping("/guest/detail-info/{reservationId}")
  public String detail(HttpServletRequest request, Model model, @PathVariable Long reservationId) {

    ReservationDto reservationDto = reservationService.findById(reservationId);
    Long roomId = reservationDto.getRoomId();

    RoomDto roomDto = roomService.findById(reservationDto.getRoomId());
    MemberDto memberDto = memberService.findById(roomDto.getMemberId());
    List<RoomImageDto> roomImageDtoList = roomImageService.findByRoomId(roomId);

    model.addAttribute("room", roomDto);
    model.addAttribute("member", memberDto);
    model.addAttribute("reservation", reservationDto);
    model.addAttribute("roomImageDtoList", roomImageDtoList);


    return "reservation/guest/detail-info";
  }

  @GetMapping("/host/list")
  public String reservationHost() {

    return "reservation/host/list";
  }

  @GetMapping("/guest/update/{reservationId}")
  public String guestReservationUpdate(Model model, @PathVariable Long reservationId) {
    ReservationDto reservationDto = reservationService.findById(reservationId);

    Long roomId = reservationDto.getRoomId();
    RoomDto roomDto = roomService.findById(roomId);

    ReservationDto reservationDto1 = new ReservationDto();
    reservationDto1.setReservationId(reservationId);
    reservationDto1.setRoomId(roomId);

    List<LocalDate> reservationDateList = reservationDateService.reservationDateUpdateList(reservationDto1);
    Collections.sort(reservationDateList);

    model.addAttribute("room", roomDto);
    model.addAttribute("reservation", reservationDto);
    model.addAttribute("reservationDateList", reservationDateList);

    return "reservation/guest/update";
  }

  @PostMapping("/guest/update/{reservationId}")
  public String guestReservationUpdateResult(ReservationDto updateReservationDto,
                                             HttpServletRequest request,
                                             Model model,
                                             @PathVariable Long reservationId) {

    ReservationDto reservationDto = reservationService.findById(reservationId);

    model.addAttribute("reservation", reservationDto);
    model.addAttribute("updateReservationDto", updateReservationDto);
    model.addAttribute("reservationDateArray", request.getParameter("reservationDateArray"));

    return "reservation/guest/update-result";
  }


  @GetMapping("/guest/cancel/reason/{reservationId}")
  public String guestReservationCancel(Model model, @PathVariable Long reservationId) {

    model.addAttribute("reservationId", reservationId);

    return "reservation/guest/cancel";
  }

  @PostMapping("/guest/cancel/confirm/{reservationId}")
  public String guestReservationCancelConfirm(@PathVariable Long reservationId) {

    return "reservation/guest/cancel-confirm";
  }

  @PostMapping(value = "/pay/complete", produces = "application/json; charset=utf-8")
  public String guestReservationPayComplete(HttpSession session, ReservationDto reservationDto,
                                            HttpServletRequest request,
                                            @RequestParam String paymentData,
                                            HttpServletResponse response, Model model) {


    MemberDto memberDto = (MemberDto) session.getAttribute("member");
    Long memberId = memberDto.getMemberId(); // 세션값 가져오기
    Long roomId = reservationDto.getRoomId();
    RoomDto roomDto = roomService.findById(roomId); // 호스트가 등록한 숙소인지 찾기 위해

    String reservationDateArr = request.getParameter("reservationDateArray");
    String[] reservationDateArray = null;
    if (reservationDateArr.substring(1).equals("[")) {
      reservationDateArray = reservationDateArr.substring(1, reservationDateArr.length() - 1).split(", ");
    } else {
      reservationDateArray = reservationDateArr.split(",");
    }


    try {
      LocalDate reservationDateCheckIn = LocalDate.parse(reservationDateArray[0].substring(1)); // parse : try catch 문 필요

      // 이미 예약된 날짜인지 확인하기 위해 숙소아이디와 체크인 날짜로 찾아봄
      ReservationDto reservationDto1 = new ReservationDto();
      reservationDto1.setRoomId(roomId);
      reservationDto1.setCheckIn(reservationDateCheckIn);
      ReservationDateDto reservationDateRoomId = reservationDateService.findByReservationDate(reservationDto1);

      if (reservationDateRoomId != null) {
        // 예약 시 선택한 체크인 날짜가 이미 예약된 날짜라면
        // 예약하지 못하게 막기
        return "redirect:/";

      } else {
        reservationDto.setMemberId(memberId);
        reservationDto.setStatus("upcoming");

        reservationService.reservationInsert(reservationDto);

        List<ReservationDateDto> reservationDateDtos = new ArrayList<>();

        for (String reservationDay : reservationDateArray) {
          ReservationDateDto reservationDateDto = new ReservationDateDto();
          reservationDateDto.setReservationId(reservationDto.getReservationId());
          reservationDateDto.setRoomId(roomId);

          LocalDate reservationDate = LocalDate.parse(reservationDay.substring(1, 11)); // parse : try catch 문 필요
          reservationDateDto.setReservationDate(reservationDate);
          reservationDateDtos.add(reservationDateDto);
        }

        reservationDateService.reservationDateInsert(reservationDateDtos);

        // 결제 테스트
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = null;

        map = mapper.readValue(paymentData, Map.class);

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId((String) map.get("imp_uid"));
        paymentDto.setReservationId(reservationDto.getReservationId());
        paymentDto.setPrice((int) map.get("paid_amount"));
        paymentDto.setCommission((int) map.get("commission"));
        paymentDto.setPaymentMethod((String) map.get("pay_method"));

        paymentService.paymentInsert(paymentDto);

      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    model.addAttribute("room", roomDto);
    model.addAttribute("reservation", reservationDto);

    return "reservation/guest/complete";
  }

  @GetMapping("/host/detail-info")
  public String reservationHostDetail() {

    return "reservation/host/detail-info";
  }

}

