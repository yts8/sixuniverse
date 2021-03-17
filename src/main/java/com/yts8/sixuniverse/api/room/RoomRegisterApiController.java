package com.yts8.sixuniverse.api.room;

import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room/register")
public class RoomRegisterApiController {

  private final ReservationDateService reservationDateService;

  @PostMapping("/calendar")
  public void postCalendar(@RequestParam List<String> impossibleDayString, @RequestParam Long roomId) {

    List<LocalDate> impossibleDay = new ArrayList<>();//db에 저장할 date타입 예약불가날짜 리스트

    for (String day : impossibleDayString) {
      LocalDate day2 = LocalDate.parse(day);

      impossibleDay.add(day2);
    }

    List<ReservationDateDto> hostReservationDtos = new ArrayList<>();
    for (LocalDate d : impossibleDay) {
      ReservationDateDto reservationDateDto = new ReservationDateDto();
      reservationDateDto.setRoomId(roomId);
      reservationDateDto.setReservationDate(d);
      hostReservationDtos.add(reservationDateDto);
    }


    reservationDateService.hostReservationDateInsert(hostReservationDtos);


  }
}
