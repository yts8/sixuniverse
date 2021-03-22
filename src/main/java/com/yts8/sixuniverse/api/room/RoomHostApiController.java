package com.yts8.sixuniverse.api.room;

import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // TODO: 유효성 검사 및 운영 중지 복구.. 리업뎃
  }
}
