package com.yts8.sixuniverse.room.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import com.yts8.sixuniverse.roomHits.dto.RoomHitsDto;
import com.yts8.sixuniverse.roomHits.service.RoomHitsService;
import com.yts8.sixuniverse.roomImage.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
  private final RoomHitsService roomHitsService;
  private final ReservationDateService reservationDateService;

  @GetMapping("/detail/{roomId}")
  public String detail(Model model, HttpSession httpSession, @PathVariable Long roomId) {

    RoomDto roomDto = roomService.findById(roomId);
    if (!roomDto.getStatus().equals("register")) {
      return "redirect:/";
    }

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    if (member == null || !roomDto.getMemberId().equals(member.getMemberId())) {
      RoomHitsDto roomHitsDto = new RoomHitsDto();
      roomHitsDto.setRoomId(roomId);
      roomHitsDto.setReadDate(LocalDate.now());

      RoomHitsDto findRoomHitsDto = roomHitsService.findByRoomIdAndReadDate(roomHitsDto);
      if (findRoomHitsDto == null) {
        roomHitsService.save(roomHitsDto);
      } else {
        roomHitsService.updateHits(findRoomHitsDto.getRoomHitsId());
      }
    }

    List<LocalDate> reservationDateList = reservationDateService.reservationDateList(roomId);
    Collections.sort(reservationDateList);

    model.addAttribute("title", "숙소 상세 정보");
    model.addAttribute("room", roomDto);
    model.addAttribute("reservationDateList", reservationDateList);
    model.addAttribute("roomImages", roomImageService.findByRoomId(roomDto.getRoomId()));
    return "room/detail";
  }


}
