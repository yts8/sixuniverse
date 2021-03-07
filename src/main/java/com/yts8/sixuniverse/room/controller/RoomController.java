package com.yts8.sixuniverse.room.controller;

import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/host/room")
public class RoomController {
  private final RoomService roomService;
}
