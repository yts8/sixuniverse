package com.yts8.sixuniverse.api.room;

import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room/register")
public class RoomRegisterApiController {

  private final ReservationDateService reservationDateService;

  @PostMapping("/calendar")
  public void postCalendar(@RequestParam List<String> impossibleDayString, @RequestParam Long roomId) {
//    System.out.println("impossibleDayString = " + impossibleDayString);

//    model.addAttribute("impossibleDayString",impossibleDayString);

    List<Date> impossibleDay = new ArrayList<>();//db에 저장할 date타입 예약불가날짜 리스트

    for (String day : impossibleDayString) {
      java.sql.Date day2 = java.sql.Date.valueOf(day);
      impossibleDay.add(day2);
    }

    List<ReservationDateDto> hostReservationDtos = new ArrayList<>();
    for (Date d : impossibleDay) {
      ReservationDateDto reservationDateDto = new ReservationDateDto();
      reservationDateDto.setRoomId(roomId);
//      reservationDateDto.setReservationDate(d);
      hostReservationDtos.add(reservationDateDto);
    }


    reservationDateService.hostReservationDateInsert(hostReservationDtos);


  }
}
