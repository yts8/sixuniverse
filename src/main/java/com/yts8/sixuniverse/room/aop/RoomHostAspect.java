package com.yts8.sixuniverse.room.aop;

import com.yts8.sixuniverse.api.room.RoomHostApiController;
import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
@RequiredArgsConstructor
public class RoomHostAspect {

  private final RoomService roomService;
  private final HttpSession httpSession;

  @Around("" +
      "execution(* com.yts8.sixuniverse.api.room.RoomHostApiController.post*(..))")
  public Object postCheckUser(ProceedingJoinPoint joinPoint) throws Throwable {
    Long roomId = (Long) joinPoint.getArgs()[0];

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    RoomDto roomDto = roomService.findById(roomId);

    if (roomDto == null || !roomDto.getMemberId().equals(member.getMemberId())) {
      if (joinPoint.getTarget() instanceof RoomHostApiController) {
        return null;
      }
      return "redirect:/";
    }
    return joinPoint.proceed();
  }

}
