package com.yts8.sixuniverse.api.reservation;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.service.ReservationService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationApiController {

  private final ReservationService reservationService;
  private final RoomService roomService;

  @PostMapping("/guest/update/today")
  public @ResponseBody boolean updateDate(@RequestBody LocalDate checkIn) {
    boolean result = false;

    LocalDate today = LocalDate.now();

    if (checkIn.equals(today)) {
      result = true;
    }
    return result;
  }


  @PostMapping("/guest/update/complete")
  public @ResponseBody void guestReservationUpdateComplete(@RequestBody ReservationDto reservationDto) {
    reservationService.guestReservationUpdateRequest(reservationDto.getReservationId());
  }

  @PostMapping("/room/member/check")
  public @ResponseBody boolean memberCheck(@RequestBody Long roomId, HttpSession session) {
    boolean result = false;

    RoomDto roomDto = roomService.findById(roomId);
    Long roomMemberId = roomDto.getMemberId();

    MemberDto memberDto = (MemberDto) session.getAttribute("member");
    Long sessionMemberId = memberDto.getMemberId();

    if(sessionMemberId==roomMemberId) {
      result = true;
    }

    return result;
  }

}


