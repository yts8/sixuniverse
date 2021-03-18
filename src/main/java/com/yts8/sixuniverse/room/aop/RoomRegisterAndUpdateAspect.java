package com.yts8.sixuniverse.room.aop;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import com.yts8.sixuniverse.roomFacility.dto.RoomFacilityDto;
import com.yts8.sixuniverse.roomFacility.service.RoomFacilityService;
import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;
import com.yts8.sixuniverse.roomImage.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class RoomRegisterAndUpdateAspect {

  private final RoomService roomService;
  private final RoomImageService roomImageService;
  private final RoomFacilityService roomFacilityService;

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

  @Around("(execution(* com.yts8.sixuniverse.room.controller.RoomRegisterAndUpdateController.post*(..)) ||" +
      "execution(* com.yts8.sixuniverse.api.room.RoomRegisterAndUpdateApiController.postImages(..))) &&" +
      "!execution(* com.yts8.sixuniverse.room.controller.RoomRegisterAndUpdateController.postAddress(..))")
  public Object postCheckUser(ProceedingJoinPoint joinPoint) throws Throwable {

    RoomDto room = (RoomDto) joinPoint.getArgs()[0];

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    RoomDto roomDto = roomService.findById(room.getRoomId());

    if (roomDto == null || !roomDto.getMemberId().equals(member.getMemberId())) {
      return "redirect:/";
    }

    Object proceed = joinPoint.proceed();

    RoomDto updateRoomDto = roomService.findById(room.getRoomId());
    List<RoomImageDto> roomImageDtos = roomImageService.findByRoomId(room.getRoomId());
    RoomFacilityDto facilityDto = new RoomFacilityDto();
    facilityDto.setRoomId(roomDto.getRoomId());
    facilityDto.setCategoryName("amenities");
    List<String> amenities = roomFacilityService.findByRoomIdAndCategoryName(facilityDto);
    facilityDto.setCategoryName("safety");
    List<String> safety = roomFacilityService.findByRoomIdAndCategoryName(facilityDto);
    facilityDto.setCategoryName("spaces");
    List<String> spaces = roomFacilityService.findByRoomIdAndCategoryName(facilityDto);

    if (updateRoomDto.getBuildingType() != null &&
        updateRoomDto.getRoomType() != null &&
        updateRoomDto.getMaxPeople() != 0 &&
        updateRoomDto.getTitle() != null &&
        updateRoomDto.getContent() != null &&
        updateRoomDto.getCheckInTime() != null &&
        updateRoomDto.getCheckOutTime() != null &&
        updateRoomDto.getPrice() != 0 &&
        roomImageDtos.size() >= 5 &&
        !amenities.isEmpty() &&
        !safety.isEmpty() &&
        !spaces.isEmpty()
    ) {
      updateRoomDto.setStatus("register");
      roomService.updateStatus(updateRoomDto);
    }

    return proceed;
  }

}
