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
public class RoomRegisterAspect {

  private final RoomService roomService;

  @Around("execution(* com.yts8.sixuniverse.room.controller.RoomRegisterController.get*(..)) && " +
      "!execution(* com.yts8.sixuniverse.room.controller.RoomRegisterController.getAddress(..)) &&" +
      "!execution(* com.yts8.sixuniverse.room.controller.RoomRegisterController.getAmenities(..))")
  public Object getCheckUser(ProceedingJoinPoint joinPoint) throws Throwable {

    Object[] methodArgs = joinPoint.getArgs();
    HttpSession httpSession = (HttpSession) methodArgs[1];

    Long roomId = (Long) methodArgs[2];
    MemberDto member = (MemberDto) httpSession.getAttribute("member");

    RoomDto roomDto = roomService.findById(roomId);
    if (roomDto == null || !roomDto.getMemberId().equals(member.getMemberId())) {
      return "redirect:/";
    }

    return joinPoint.proceed();
  }

  @Around("execution(* com.yts8.sixuniverse.room.controller.RoomRegisterController.post*(..)) && " +
      "!execution(* com.yts8.sixuniverse.room.controller.RoomRegisterController.postAddress(..)) &&" +
      "!execution(* com.yts8.sixuniverse.room.controller.RoomRegisterController.postAmenities(..))")
  public Object postCheckUser(ProceedingJoinPoint joinPoint) throws Throwable {

    HttpSession httpSession = (HttpSession) joinPoint.getArgs()[0];
    RoomDto room = (RoomDto) joinPoint.getArgs()[1];

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    RoomDto roomDto = roomService.findById(room.getRoomId());
    if (roomDto == null || !roomDto.getMemberId().equals(member.getMemberId())) {
      return "redirect:/";
    }

    return joinPoint.proceed();
  }


}
