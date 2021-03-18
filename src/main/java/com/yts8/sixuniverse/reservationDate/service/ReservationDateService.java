package com.yts8.sixuniverse.reservationDate.service;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDateService {

  ReservationDateDto findByReservationDate(ReservationDto reservationDto);

  void reservationDateInsert(List<ReservationDateDto> reservationDateDtos);

  void hostReservationDateInsert(List<ReservationDateDto> reservationDateDto);

  List<LocalDate> reservationDateList(Long roomId);

  List<LocalDate> reservationDateUpdateList(ReservationDto reservationDto);

  void guestReservationDateDelete(Long reservationId);

}
