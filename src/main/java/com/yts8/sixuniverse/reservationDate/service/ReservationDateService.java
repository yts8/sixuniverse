package com.yts8.sixuniverse.reservationDate.service;

import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;

import java.util.Date;
import java.util.List;

public interface ReservationDateService {

  ReservationDateDto findByReservationDate(Long roomId, Date reservationDate);

  void reservationDateInsert(ReservationDateDto reservationDateDto);

  List<String> reservationDateList(Long roomId);

}
