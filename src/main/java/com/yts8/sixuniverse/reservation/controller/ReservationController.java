package com.yts8.sixuniverse.reservation.controller;

import com.google.gson.Gson;
import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import com.yts8.sixuniverse.payment.dto.PaymentDto;
import com.yts8.sixuniverse.payment.service.PaymentService;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.dto.ReservationRoomPaymentDto;
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
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    MemberDto hostInfo = memberService.findById(roomMemberId);

    LocalDate checkIn = reservationDto.getCheckIn();
//    int days = Period.between(checkIn, reservationDto.getCheckOut()).getDays();
    Long days = ChronoUnit.DAYS.between(checkIn, reservationDto.getCheckOut());

    List<LocalDate> reservationDateArray = new ArrayList<>();
    reservationDateArray.add(checkIn);
    for (int i = 1; i <= days; i++) {
      LocalDate reservationDate = checkIn.plusDays(i);
      reservationDateArray.add(reservationDate);
    }

    Collections.sort(reservationDateArray);

    if (sessionMemberId.equals(roomMemberId)) {
      return "redirect:/";
    }
    int commission = Integer.parseInt(request.getParameter("commission"));
    int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));

    List<LocalDate> reservationDateList = reservationDateService.reservationDateList(roomId);
    Collections.sort(reservationDateList);

    model.addAttribute("title", "예약하기");
    model.addAttribute("room", roomDto);
    model.addAttribute("hostInfo", hostInfo);
    model.addAttribute("beforeReservation", reservationDto);
    model.addAttribute("reservationDateArray", reservationDateArray);
    model.addAttribute("commission", commission);
    model.addAttribute("totalPrice", totalPrice);
    model.addAttribute("reservationDateList", reservationDateList);
    model.addAttribute("roomImages", roomImageService.findByRoomId(roomId));

    return "reservation/index";
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

    for (ReservationDto reservation : reservationList) {
      Long roomId = reservation.getRoomId();

      RoomDto roomDto = roomService.findById(roomId);
      List<RoomImageDto> roomImageDto = roomImageService.findByRoomId(roomId);
      roomList.add(roomDto);
      roomImageList.add(roomImageDto);
    }

    List<ReservationDto> updateList = reservationService.updateList();

    model.addAttribute("title", "예약목록");
    model.addAttribute("status", status);
    model.addAttribute("roomList", roomList);
    model.addAttribute("roomImageList", roomImageList);
    model.addAttribute("reservationList", reservationList);
    model.addAttribute("updateList", updateList);

    return "reservation/guest/list";
  }

  @GetMapping("/guest/simple-info/{reservationId}")
  public String simple(Model model, @PathVariable Long reservationId) {
    ReservationDto reservationDto = reservationService.findById(reservationId);
    Long roomId = reservationDto.getRoomId();

    RoomDto roomDto = roomService.findById(reservationDto.getRoomId());
    MemberDto memberDto = memberService.findById(roomDto.getMemberId());
    List<RoomImageDto> roomImageDtoList = roomImageService.findByRoomId(roomId);

    model.addAttribute("title", "예약정보");
    model.addAttribute("reservation", reservationDto);
    model.addAttribute("room", roomDto);
    model.addAttribute("hostId", memberDto.getMemberId());
    model.addAttribute("roomImageDtoList", roomImageDtoList);

    return "reservation/guest/simple-info";
  }


  @GetMapping("/guest/detail-info/{reservationId}")
  public String detail(Model model, @PathVariable Long reservationId) {

    ReservationDto reservationDto = reservationService.findById(reservationId);
    Long roomId = reservationDto.getRoomId();

    RoomDto roomDto = roomService.findById(reservationDto.getRoomId());
    MemberDto memberDto = memberService.findById(roomDto.getMemberId());
    List<RoomImageDto> roomImageDtoList = roomImageService.findByRoomId(roomId);
    PaymentDto paymentDto = paymentService.findByReservationId(reservationId);

    model.addAttribute("title", "예약정보");
    model.addAttribute("room", roomDto);
    model.addAttribute("member", memberDto);
    model.addAttribute("payment", paymentDto);
    model.addAttribute("reservation", reservationDto);
    model.addAttribute("roomImageDtoList", roomImageDtoList);

    return "reservation/guest/detail-info";
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

    PaymentDto paymentDto = paymentService.findByReservationId(reservationId);
    List<RoomImageDto> roomImages = roomImageService.findByRoomId(roomId);

    model.addAttribute("title", "예약변경");
    model.addAttribute("room", roomDto);
    model.addAttribute("payment", paymentDto);
    model.addAttribute("roomImages", roomImages);
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
    Long roomId = reservationDto.getRoomId();
    RoomDto roomDto = roomService.findById(roomId);
    PaymentDto paymentDto = paymentService.findByReservationId(reservationId);
    List<RoomImageDto> roomImages = roomImageService.findByRoomId(roomId);

    LocalDate checkIn = updateReservationDto.getCheckIn();
    LocalDate checkOut = updateReservationDto.getCheckOut();

//    int days = Period.between(checkIn, checkOut).getDays();
    Long days = ChronoUnit.DAYS.between(checkIn, checkOut);

    Long price = roomDto.getPrice() * days + (int) ((roomDto.getPrice() * days) * 0.1);

    model.addAttribute("title", "예약변경");
    model.addAttribute("room", roomDto);
    model.addAttribute("price", price);
    model.addAttribute("payment", paymentDto);
    model.addAttribute("roomImages", roomImages);
    model.addAttribute("reservation", reservationDto);
    model.addAttribute("updateReservationDto", updateReservationDto);
    model.addAttribute("reservationDateArray", request.getParameter("reservationDateArray"));

    return "reservation/guest/update-result";
  }

  @GetMapping("/guest/cancel/reason/{reservationId}")
  public String guestReservationCancel(Model model, @PathVariable Long reservationId) {
    ReservationRoomPaymentDto reservationRoomPaymentDto = reservationService.findByCancelReservationId(reservationId);

    model.addAttribute("title", "예약취소");
    model.addAttribute("reservationRPDto", reservationRoomPaymentDto);
    model.addAttribute("roomImages", roomImageService.findByRoomId(reservationRoomPaymentDto.getRoomId()));

    return "reservation/guest/cancel";
  }

  @PostMapping("/guest/cancel/confirm/{reservationId}")
  public String guestReservationCancelConfirm(Model model, @PathVariable Long reservationId) {
    ReservationRoomPaymentDto reservationRoomPaymentDto = reservationService.findByCancelReservationId(reservationId);

    model.addAttribute("title", "예약취소");
    model.addAttribute("reservationRPDto", reservationRoomPaymentDto);
    model.addAttribute("roomImages", roomImageService.findByRoomId(reservationRoomPaymentDto.getRoomId()));

    return "reservation/guest/cancel-confirm";
  }

  @PostMapping(value = "/pay/complete", produces = "application/json; charset=utf-8")
  public String guestReservationPayComplete(HttpSession session, ReservationDto reservationDto,
                                            HttpServletRequest request,
                                            @RequestParam String paymentData,
                                            Model model) {

    MemberDto memberDto = (MemberDto) session.getAttribute("member");
    Long memberId = memberDto.getMemberId(); // 세션값 가져오기
    Long roomId = reservationDto.getRoomId();
    RoomDto roomDto = roomService.findById(roomId); // 호스트가 등록한 숙소인지 찾기 위해
    List<RoomImageDto> roomImageDto = roomImageService.findByRoomId(roomId);

    String reservationDateArr = request.getParameter("reservationDateArray");

    String[] reservationDateArray = null;
    if (reservationDateArr.charAt(0) == '[') {
      reservationDateArray = reservationDateArr.substring(1, reservationDateArr.length() - 1).split(", ");
    } else {
      reservationDateArray = reservationDateArr.split(",");
    }

    PaymentDto afterPaymentDto = null;

    try {
      LocalDate reservationDateCheckIn = LocalDate.parse(reservationDateArray[0]); // parse : try catch 문 필요

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
        reservationDto.setCreateDate(LocalDateTime.now());

        reservationService.reservationInsert(reservationDto);

        List<ReservationDateDto> reservationDateDtos = new ArrayList<>();

        for (String reservationDay : reservationDateArray) {
          ReservationDateDto reservationDateDto = new ReservationDateDto();
          reservationDateDto.setReservationId(reservationDto.getReservationId());
          reservationDateDto.setRoomId(roomId);

          LocalDate reservationDate = LocalDate.parse(reservationDay); // parse : try catch 문 필요
          reservationDateDto.setReservationDate(reservationDate);
          reservationDateDtos.add(reservationDateDto);
        }

        reservationDateService.reservationDateInsert(reservationDateDtos);

        // 결제 테스트
        Gson gson = new Gson();
        Map map = gson.fromJson(paymentData, Map.class);

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId((String) map.get("imp_uid"));
        paymentDto.setReservationId(reservationDto.getReservationId());
        paymentDto.setPrice((int) ((double) map.get("paid_amount")));
        paymentDto.setCommission((int) ((double) map.get("commission")));
        paymentDto.setPaymentMethod((String) map.get("pay_method"));

        paymentService.paymentInsert(paymentDto);

        afterPaymentDto = paymentService.findByReservationId(reservationDto.getReservationId());

      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    model.addAttribute("title", "예약완료");
    model.addAttribute("memberId", memberId);
    model.addAttribute("room", roomDto);
    model.addAttribute("reservation", reservationDto);
    model.addAttribute("payment", afterPaymentDto);
    model.addAttribute("roomImages", roomImageDto);

    return "reservation/guest/complete";
  }

}
