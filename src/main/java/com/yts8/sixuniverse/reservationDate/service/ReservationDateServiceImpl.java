package com.yts8.sixuniverse.reservationDate.service;

import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservationDate.repository.ReservationDateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationDateServiceImpl implements ReservationDateService {

  private final ReservationDateMapper reservationDateMapper;

  @Override
  public ReservationDateDto findByReservationDate(Long roomId, Date reservationDate) {
    return reservationDateMapper.findByReservationDate(roomId, reservationDate);
  }

  @Override
  public void reservationDateInsert(ReservationDateDto reservationDateDto) {
    reservationDateMapper.reservationDateInsert(reservationDateDto);
  }

  @Override
  public List<String> reservationDateList(Long roomId) {
    return reservationDateMapper.reservationDateList(roomId);
  }
}
