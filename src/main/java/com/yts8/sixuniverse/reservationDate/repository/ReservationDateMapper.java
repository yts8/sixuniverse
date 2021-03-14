package com.yts8.sixuniverse.reservationDate.repository;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper
public interface ReservationDateMapper {

  ReservationDateDto findByReservationDate(ReservationDto reservationDto);

  void reservationDateInsert(List<ReservationDateDto> reservationDateDtos);

  void hostReservationDateInsert(List<ReservationDateDto> reservationDateDtos);

  List<LocalDate> reservationDateList(Long roomId);

  List<LocalDate> reservationDateUpdateList(ReservationDto reservationDto);
}
