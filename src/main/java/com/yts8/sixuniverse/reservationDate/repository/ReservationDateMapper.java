package com.yts8.sixuniverse.reservationDate.repository;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface ReservationDateMapper {

  ReservationDateDto findByReservationDate(Long roomId, Date reservationDate);

  void reservationDateInsert(List<ReservationDateDto> reservationDateDtos);

  List<String> reservationDateList(Long roomId);

  List<String> reservationDateUpdateList(ReservationDto reservationDto);
}
