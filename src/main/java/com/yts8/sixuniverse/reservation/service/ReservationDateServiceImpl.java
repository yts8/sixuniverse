package com.yts8.sixuniverse.reservation.service;

import com.yts8.sixuniverse.reservation.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservation.repository.ReservationDateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationDateServiceImpl implements ReservationDateService {

  private final ReservationDateMapper reservationDateMapper;

  @Override
  public void reservationDateInsert(ReservationDateDto reservationDateDto) {
    reservationDateMapper.reservationDateInsert(reservationDateDto);
  }

  @Override
  public List<String> reservationDateList(Long roomId) {
    return reservationDateMapper.reservationDateList(roomId);
  }
}
