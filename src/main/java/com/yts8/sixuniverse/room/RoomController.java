package com.yts8.sixuniverse.room;

import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;

  @GetMapping("/room/regi-addr")
  public String RegiRoom() {
    return "regi-addr";
  }

  @PostMapping("/room/regi-addr")
  public String RegiRoomPost() {
    roomService.RoomRegi();
    return null;
  }


}
