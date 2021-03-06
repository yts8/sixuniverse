package com.yts8.sixuniverse.reservation.controller;

import com.yts8.sixuniverse.reservation.service.TestRoomService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestRoomController {

  private final TestRoomService testRoomService;

  @GetMapping("/room/list")
  public String reservation(Model model) {
    List<RoomDto> roomList = testRoomService.roomList();

    model.addAttribute("roomList", roomList);
    return "reservation/room/list";
  }





}
