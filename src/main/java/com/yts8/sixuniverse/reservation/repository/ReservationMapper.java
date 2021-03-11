package com.yts8.sixuniverse.reservation.repository;

import com.yts8.sixuniverse.reservation.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
  void reservationInsert(ReservationDto reservationDto);

  List<ReservationDto> reservationList(Long memberId, String status);

  ReservationDto findById(Long reservationId);
}
