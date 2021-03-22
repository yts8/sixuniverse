package com.yts8.sixuniverse.room.scheduler;

import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RoomScheduling {

  private final RoomService roomService;

  @Scheduled(cron = "0 0 0 * * *")
  public void roomRenewScheduling() {
    roomService.updateExpiry(LocalDateTime.now());
  }
}
