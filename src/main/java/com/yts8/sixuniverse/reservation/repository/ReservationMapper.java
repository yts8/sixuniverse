package com.yts8.sixuniverse.reservation.repository;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.dto.ReservationRoomDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReservationMapper {
  void reservationInsert(ReservationDto reservationDto);

  List<ReservationDto> reservationList(ReservationDto reservationDto);

  ReservationDto findById(Long reservationId);

  void reservationCheckOut(LocalDate today);

  void guestReservationUpdateRequest(Long reservationId);

  void guestReservationUpdateInsert(ReservationDto reservationDto);

  void guestReservationCancel(ReservationDto reservationDto);

  List<ReservationRoomDto> findByUpdateReservationId(Long reservationId);

  ReservationRoomDto findByCancelReservationId(Long reservationId);
}
