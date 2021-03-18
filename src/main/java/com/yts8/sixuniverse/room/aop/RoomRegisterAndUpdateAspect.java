package com.yts8.sixuniverse.room.aop;

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
public class RoomRegisterAndUpdateAspect {

  private final RoomService roomService;
  private final HttpSession httpSession;

  @Around("execution(* com.yts8.sixuniverse.room.controller.RoomRegisterAndUpdateController.get*(..)) && " +
      "!execution(* com.yts8.sixuniverse.room.controller.RoomRegisterAndUpdateController.getAddressRegister(..))")
  public Object getCheckUser(ProceedingJoinPoint joinPoint) throws Throwable {

    Long roomId = (Long) joinPoint.getArgs()[1];

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    RoomDto roomDto = roomService.findById(roomId);

    if (roomDto == null || !roomDto.getMemberId().equals(member.getMemberId())) {
      return "redirect:/";
    }

    return joinPoint.proceed();
  }

  @Around("execution(* com.yts8.sixuniverse.room.controller.RoomRegisterAndUpdateController.post*(..)) && " +
      "!execution(* com.yts8.sixuniverse.room.controller.RoomRegisterAndUpdateController.postAddress(..))")
  public Object postCheckUser(ProceedingJoinPoint joinPoint) throws Throwable {

    RoomDto room = (RoomDto) joinPoint.getArgs()[0];

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    RoomDto roomDto = roomService.findById(room.getRoomId());

    if (roomDto == null || !roomDto.getMemberId().equals(member.getMemberId())) {
      return "redirect:/";
    }

    return joinPoint.proceed();
  }

}
