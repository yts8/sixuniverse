package com.yts8.sixuniverse.reservation.repository;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.dto.HostReservationDto;
import com.yts8.sixuniverse.reservation.dto.ReservationRoomPaymentDto;
import com.yts8.sixuniverse.review.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReservationMapper {
  void reservationInsert(ReservationDto reservationDto);

  List<ReservationDto> reservationList(ReservationDto reservationDto);

  List<HostReservationDto> hostReservationList(ReservationDto reservationDto);

  ReservationDto findById(Long reservationId);

  void reservationCheckOut(LocalDate today);

  void guestReservationUpdateRequest(Long reservationId);

  void guestReservationUpdateInsert(ReservationDto reservationDto);

  void guestReservationCancel(ReservationDto reservationDto);

  ReservationDto findByUpdateTarget(Long reservationId);

  ReviewDto findByRoomIdAndMemberId(ReservationDto reservationDto);

  List<ReservationRoomPaymentDto> findByUpdateReservationId(Long reservationId);

  ReservationRoomPaymentDto findByCancelReservationId(Long reservationId);
}
