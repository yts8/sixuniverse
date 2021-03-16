package com.yts8.sixuniverse.reservation.repository;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReservationMapper {
  void reservationInsert(ReservationDto reservationDto);

  List<ReservationDto> reservationList(ReservationDto reservationDto);

  ReservationDto findById(Long reservationId);

  void reservationCheckOut(LocalDate today);
}
