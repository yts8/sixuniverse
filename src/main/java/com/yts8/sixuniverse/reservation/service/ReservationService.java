package com.yts8.sixuniverse.reservation.service;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.dto.ReservationRoomPaymentDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

  void reservationInsert(ReservationDto reservationDto);

  ReservationDto findById(Long reservationId);

  List<ReservationDto> reservationList(ReservationDto reservationDto);

  void reservationCheckOut(LocalDate today);

  void guestReservationUpdateRequest(Long reservationId);

  void guestReservationUpdateInsert(ReservationDto reservationDto);

  void guestReservationCancel(ReservationDto reservationDto);

  List<ReservationRoomPaymentDto> findByUpdateReservationId(Long reservationId);

  ReservationRoomPaymentDto findByCancelReservationId(Long reservationId);
}
