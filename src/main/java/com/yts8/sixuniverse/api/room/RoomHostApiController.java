package com.yts8.sixuniverse.api.room;

import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/host/room")
public class RoomHostApiController {

  private final RoomService roomService;

  @PostMapping("{roomId}/delete")
  public void postRoomDelete(@PathVariable Long roomId) {
    RoomDto roomDto = roomService.findById(roomId);
    if (roomDto.getStatus().equals("not register")) {
      roomService.remove(roomId);
    } else {
      roomDto.setStatus("remove");
      roomService.updateStatus(roomDto);
    }
  }

  @PostMapping("{roomId}/update/stop")
  public void postRoomUpdateStop(@PathVariable Long roomId) {
    RoomDto roomDto = roomService.findById(roomId);
    if (roomDto.getStatus().equals("register")) {
      roomDto.setStatus("stop");
      roomService.updateStatus(roomDto);
    }
  }

  @PostMapping("{roomId}/update/clear")
  public void postRoomUpdateClear(@PathVariable Long roomId) {
    RoomDto roomDto = roomService.findById(roomId);
    if (roomDto.getStatus().equals("stop")) {
      roomDto.setStatus("register");
      roomService.updateStatus(roomDto);
    }
  }

  @PostMapping("{roomId}/update/renew")
  public void postRoomUpdateRenew(@PathVariable Long roomId) {
    RoomDto roomDto = roomService.findById(roomId);
    if (roomDto.getStatus().equals("expiry")) {
      roomDto.setRenewDate(LocalDateTime.now());
      roomService.updateRenew(roomDto);
    }
  }
}
