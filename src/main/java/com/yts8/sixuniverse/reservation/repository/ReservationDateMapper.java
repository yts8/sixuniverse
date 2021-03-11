package com.yts8.sixuniverse.reservation.repository;

import com.yts8.sixuniverse.reservation.dto.ReservationDateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface ReservationDateMapper {
  void reservationDateInsert(ReservationDateDto reservationDateDto);
  List<String> reservationDateList(Long roomId);

}
