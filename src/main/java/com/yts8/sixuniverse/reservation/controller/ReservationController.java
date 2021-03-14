package com.yts8.sixuniverse.reservation.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.service.ReservationService;
import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

  private final ReservationService reservationService;
  private final RoomService roomService;
  private final MemberService memberService;
  private final ReservationDateService reservationDateService;

  @GetMapping("")
  public String reservation(HttpServletRequest request, Model model) {
    Long roomId = Long.parseLong(request.getParameter("roomId"));
    RoomDto roomDto = roomService.findById(roomId);
    int commission = (int) (roomDto.getPrice() * 0.1);

    List<LocalDate> reservationDateList = reservationDateService.reservationDateList(roomId);
    Collections.sort(reservationDateList);

    model.addAttribute("room", roomDto);
    model.addAttribute("commission", commission);
    model.addAttribute("reservationDateList", reservationDateList);

    return "reservation/index";
  }

  @GetMapping("/guest/list")
  public String guestReservation() {

    return "redirect:/reservation/guest/list/upcoming";
  }

  @GetMapping("/guest/list/{status}")
  public String guestReservation(HttpServletRequest request, HttpSession session, Model model, @PathVariable String status) {
    MemberDto memberDto = (MemberDto) session.getAttribute("member");
    Long memberId = memberDto.getMemberId();

    ReservationDto reservationDto = new ReservationDto();
    reservationDto.setMemberId(memberId);
    reservationDto.setStatus(status);

    List<ReservationDto> reservationList = reservationService.reservationList(reservationDto);

    List<RoomDto> roomList = new ArrayList<>();

    for (int i = 0; i < reservationList.size(); i++) {
      ReservationDto reservation = reservationList.get(i);

      RoomDto roomDto = roomService.findById(reservation.getRoomId());
      roomList.add(roomDto);
    }
    System.out.println("reservationList = " + reservationList);

    model.addAttribute("roomList", roomList);
    model.addAttribute("reservationList", reservationList);

    return "reservation/guest/list";
  }

  @GetMapping("/guest/simple-info/{reservationId}")
  public String simple(Model model, @PathVariable Long reservationId) {

    ReservationDto reservationDto = reservationService.findById(reservationId);
    RoomDto roomDto = roomService.findById(reservationDto.getRoomId());

    model.addAttribute("reservation", reservationDto);
    model.addAttribute("room", roomDto);

    return "reservation/guest/simple-info";
  }


  @GetMapping("/guest/detail-info/{reservationId}")
  public String detail(HttpServletRequest request, Model model, @PathVariable Long reservationId) {

    ReservationDto reservationDto = reservationService.findById(reservationId);
    RoomDto roomDto = roomService.findById(reservationDto.getRoomId());
    MemberDto memberDto = memberService.findById(roomDto.getMemberId());

    model.addAttribute("room", roomDto);
    model.addAttribute("member", memberDto);
    model.addAttribute("reservation", reservationDto);

    return "reservation/guest/detail-info";
  }

  @GetMapping("/host-reservation-list")
  public String reservationHost() {

    return "reservation/host-reservation-list";
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

  @PostMapping("/pay/complete")
  public String guestReservationPayComplete(HttpSession session, ReservationDto reservationDto,
                                            HttpServletRequest request,
                                            HttpServletResponse response, Model model) {

    MemberDto memberDto = (MemberDto) session.getAttribute("member");
    Long memberId = memberDto.getMemberId(); // 세션값 가져오기
    Long roomId = reservationDto.getRoomId();
    RoomDto roomDto = roomService.findById(roomId); // 호스트가 등록한 숙소인지 찾기 위해

    String reservationDateArr = request.getParameter("reservationDateArray");
    String[] reservationDateArray = reservationDateArr.split(",");

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
        PrintWriter out = response.getWriter();   // getWriter : try catch 문 필요
        out.println("<script>");
        out.println("alert('이미 예약된 날짜입니다.')");
        out.println("history.back()");
        out.println("</script>");


      } else {
        reservationDto.setMemberId(memberId);
        reservationDto.setStatus("upcoming");

        reservationService.reservationInsert(reservationDto);

        List<ReservationDateDto> reservationDateDtos = new ArrayList<>();
        for (int i = 0; i < reservationDateArray.length; i++) {
          ReservationDateDto reservationDateDto = new ReservationDateDto();
          reservationDateDto.setReservationId(reservationDto.getReservationId());
          reservationDateDto.setRoomId(roomId);

          LocalDate reservationDate = LocalDate.parse(reservationDateArray[i]); // parse : try catch 문 필요

          reservationDateDto.setReservationDate(reservationDate);
          reservationDateDtos.add(reservationDateDto);
        }

        reservationDateService.reservationDateInsert(reservationDateDtos);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    model.addAttribute("room", roomDto);
    model.addAttribute("reservation", reservationDto);

    return "reservation/guest/complete";
  }

}
