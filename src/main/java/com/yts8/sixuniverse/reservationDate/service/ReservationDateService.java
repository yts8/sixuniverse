package com.yts8.sixuniverse.reservationDate.service;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;

import java.util.Date;
import java.util.List;

public interface ReservationDateService {

  ReservationDateDto findByReservationDate(Long roomId, Date reservationDate);

  void reservationDateInsert(List<ReservationDateDto> reservationDateDtos);

  List<String> reservationDateList(Long roomId);

  List<String> reservationDateUpdateList(ReservationDto reservationDto);

}
