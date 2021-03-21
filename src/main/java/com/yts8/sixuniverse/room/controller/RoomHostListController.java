package com.yts8.sixuniverse.room.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;
import com.yts8.sixuniverse.roomImage.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/host/room")
public class RoomHostListController {

  private final RoomService roomService;
  private final RoomImageService roomImageService;
  private final HttpSession httpSession;

  @GetMapping("/list")
  public String getList(Model model) {
    MemberDto member = (MemberDto) httpSession.getAttribute("member");

    List<RoomDto> roomDtos = roomService.findByMemberId(member.getMemberId());
    List<RoomImageDto> roomImageDtos = new ArrayList<>();
    for (RoomDto roomDto : roomDtos) {
      roomImageDtos.add(roomImageService.findOneByRoomId(roomDto.getRoomId()));
    }
    model.addAttribute("roomDtos", roomDtos);
    model.addAttribute("roomImageDtos", roomImageDtos);
    model.addAttribute("title", "숙소 목록");
    return "room/host/list";
  }

}
