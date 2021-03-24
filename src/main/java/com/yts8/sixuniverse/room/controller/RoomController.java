package com.yts8.sixuniverse.room.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import com.yts8.sixuniverse.roomImage.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
  private final RoomService roomService;
  private final RoomImageService roomImageService;
  private final ReservationDateService reservationDateService;

  @GetMapping("/detail/{roomId}")
  public String detail(HttpSession session, HttpServletRequest request, Model model, @PathVariable Long roomId) {
    model.addAttribute("title", "숙소 상세 정보");

    roomService.updateReadCount(roomId);

    MemberDto member = (MemberDto) session.getAttribute("member");
    RoomDto roomDto = roomService.findById(roomId);
    model.addAttribute("room", roomDto);

    List<LocalDate> reservationDateList = reservationDateService.reservationDateList(roomId);
    Collections.sort(reservationDateList);
    model.addAttribute("reservationDateList", reservationDateList);

//    model.addAttribute("hostId", getHostId());

    model.addAttribute("roomImages", roomImageService.findByRoomId(roomDto.getRoomId()));
    return "room/detail";
  }


}
