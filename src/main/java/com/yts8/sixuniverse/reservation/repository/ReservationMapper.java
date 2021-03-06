package com.yts8.sixuniverse.reservation.repository;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
  void reservationInsert(ReservationDto reservationDto);
}
