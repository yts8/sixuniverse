package com.yts8.sixuniverse.reservation.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.service.ReservationService;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

  private final ReservationService reservationService;
  private final RoomService roomService;
  private final MemberService memberService;

  @GetMapping("")
  public String reservation(HttpServletRequest request, Model model) {
    Long roomId = Long.parseLong(request.getParameter("roomId"));

    RoomDto roomDto = roomService.findById(roomId);
    int commission = (int) (roomDto.getPrice() * 0.1);

    model.addAttribute("room", roomDto);
    model.addAttribute("commission", commission);
    return "reservation/index";
  }

  @GetMapping("/guest/list")
  public String guestReservation(HttpServletRequest request, HttpSession session, Model model) {
    MemberDto memberDto = (MemberDto) session.getAttribute("member");
    Long memberId = memberDto.getMemberId();

    String status = request.getParameter("status");

    if (status == null) {
      status = "upcoming";
    }

    List<ReservationDto> reservationList = reservationService.reservationList(memberId, status);
    List<RoomDto> roomList = new ArrayList<>();

    for (int i = 0; i < reservationList.size(); i++) {
      ReservationDto reservation = reservationList.get(i);

      RoomDto roomDto = roomService.findById(reservation.getRoomId());
      roomList.add(roomDto);
    }

    model.addAttribute("reservationList", reservationList);
    model.addAttribute("roomList", roomList);

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

    model.addAttribute("reservation", reservationDto);
    model.addAttribute("room", roomDto);
    model.addAttribute("member", memberDto);

    return "reservation/guest/detail-info";
  }

  @GetMapping("/host-reservation-list")
  public String reservationHost() {

    return "reservation/host-reservation-list";
  }

  @GetMapping("/guest/update")
  public String guestReservationUpdate() {

    return "reservation/guest/update";
  }

  @PostMapping("/guest/update")
  public String guestReservationUpdateResult() {

    return "reservation/guest/update-result";
  }


  @GetMapping("/guest/update/complete")
  public String guestReservationUpdateComplete() {

    return "reservation/guest/update-result";
  }

  @GetMapping("/guest/cancel")
  public String guestReservationCancel() {

    return "reservation/guest/cancel";
  }

  @GetMapping("/guest/cancel/next")
  public String guestReservationCancelNext() {

    return "reservation/guest/cancel";
  }

  @GetMapping("/pay/complete")
  public String guestReservationPayComplete(HttpSession session, ReservationDto reservationDto) {
    MemberDto memberDto = (MemberDto) session.getAttribute("member");
    reservationDto.setMemberId(memberDto.getMemberId());
    reservationDto.setStatus("upcoming");

    reservationService.reservationInsert(reservationDto);

    return "reservation/guest/complete";
  }

}
