package com.yts8.sixuniverse.performance.scheduler;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.performance.service.PerformanceService;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class SuperHostScheduling {

  private final PerformanceService performanceService;

  @Scheduled(cron = "0 0 1 1 * *")

  public void superHostScheduling() {
    performanceService.updateSuperHostScheduler();


  }
}
