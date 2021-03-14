package com.yts8.sixuniverse.reservation.service;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

  void reservationInsert(ReservationDto reservationDto);

  ReservationDto findById(Long reservationId);

  List<ReservationDto> reservationList(ReservationDto reservationDto);

}
