package com.yts8.sixuniverse.reservation.service;

import com.yts8.sixuniverse.reservation.dto.ReservationDateDto;

import java.util.Date;
import java.util.List;

public interface ReservationDateService {
  void reservationDateInsert(ReservationDateDto reservationDateDto);

  List<String> reservationDateList(Long roomId);

}
