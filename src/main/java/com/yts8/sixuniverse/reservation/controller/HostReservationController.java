package com.yts8.sixuniverse.reservation.controller;


import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.reservation.dto.HostDetailInfoDto;
import com.yts8.sixuniverse.reservation.dto.HostReservationDto;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/host/reservation")
public class HostReservationController {

  private final ReservationService reservationService;

  @GetMapping("/list")
  public String hostReservation() {

    return "redirect:/host/reservation/list/upcoming";
  }

  @GetMapping("/list/{status}")
  public String hostReservation(HttpSession session, Model model, @PathVariable String status) {
    MemberDto memberDto = (MemberDto) session.getAttribute("member");

    ReservationDto reservationDto = new ReservationDto();
    reservationDto.setMemberId(memberDto.getMemberId());
    reservationDto.setStatus(status);

    List<HostReservationDto> hostReservationList = reservationService.hostReservationList(reservationDto);
    List<ReservationDto> updateList = reservationService.updateList();

    model.addAttribute("title", "예약정보");
    model.addAttribute("status", status);
    model.addAttribute("updateList", updateList);
    model.addAttribute("hostReservationList", hostReservationList);

    return "reservation/host/list";
  }

  @GetMapping("/detail-info/{reservationId}")
  public String hostDetail(Model model, HttpSession session, @PathVariable Long reservationId) {
    MemberDto member = (MemberDto) session.getAttribute("member");
    HostDetailInfoDto hostDetailInfoDto = reservationService.HostDetailInfo(reservationId);
    if (!member.getMemberId().equals(hostDetailInfoDto.getHostMemberId())) {
      return "redirect:/";
    }
    model.addAttribute("hostDetailInfoDto", hostDetailInfoDto);
    model.addAttribute("title", "예약세부정보");

    System.out.println(hostDetailInfoDto);
    
    return "reservation/host/detail-info";
  }

}
