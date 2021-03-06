package com.yts8.sixuniverse.reservation.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.service.ReservationService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("")
  public String reservation(HttpServletRequest request, Model model) {
    Long roomId = Long.parseLong(request.getParameter("roomId"));

    RoomDto roomDto = roomService.findById(roomId);

    model.addAttribute("room", roomDto);
    return "reservation/index";
  }

  @GetMapping("/guest/list")
  public String guestReservation(HttpServletRequest request, Model model) {

    String status = request.getParameter("status");
    List<String> testList = null;
      if(status==null || status.equals("upcoming")) {
        testList = new ArrayList<String>();
        testList.add("upcoming");
        testList.add("upcoming");
        testList.add("upcoming");
        testList.add("upcoming");

      } else if(status.equals("complete")) {
        testList = new ArrayList<String>();
        testList.add("complete");
        testList.add("complete");
        testList.add("complete");
        testList.add("complete");
        testList.add("complete");
        testList.add("complete");

      } else {
        testList = new ArrayList<String>();
        testList.add("cancel");
        testList.add("cancel");
        testList.add("cancel");
      }

    model.addAttribute("testList", testList);
    return "reservation/guest/list";
  }

  @GetMapping("/guest/simple-info")
  public String simple(HttpServletRequest request, Model model) {

    String status = request.getParameter("status");

    model.addAttribute("status", status);

    return "reservation/guest/simple-info";
  }


  @GetMapping("/guest/detail-info")
  public String detail(HttpServletRequest request, Model model) {

    String status = request.getParameter("status");

    model.addAttribute("status", status);

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
