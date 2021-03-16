package com.yts8.sixuniverse.reservationDate.service;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservationDate.repository.ReservationDateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationDateServiceImpl implements ReservationDateService {

  private final ReservationDateMapper reservationDateMapper;

  @Override
  public ReservationDateDto findByReservationDate(ReservationDto reservationDto) {
    return reservationDateMapper.findByReservationDate(reservationDto);
  }

  @Override
  public void reservationDateInsert(List<ReservationDateDto> reservationDateDtos) {
    reservationDateMapper.reservationDateInsert(reservationDateDtos);
  }

  @Override
  public void hostReservationDateInsert(List<ReservationDateDto> reservationDateDto) {
    reservationDateMapper.hostReservationDateInsert(reservationDateDto);
  }

  @Override
  public List<LocalDate> reservationDateList(Long roomId) {
    return reservationDateMapper.reservationDateList(roomId);
  }

  @Override
  public List<LocalDate> reservationDateUpdateList(ReservationDto reservationDto) {
    return reservationDateMapper.reservationDateUpdateList(reservationDto);
  }
}
