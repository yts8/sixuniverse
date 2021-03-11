package com.yts8.sixuniverse.reservation.service;

import com.yts8.sixuniverse.reservation.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.repository.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

  private final ReservationMapper reservationMapper;

  @Override
  public void reservationInsert(ReservationDto reservationDto) {
    reservationMapper.reservationInsert(reservationDto);
  }

  @Override
  public ReservationDto findById(Long reservationId) {
    return reservationMapper.findById(reservationId);
  }

  @Override
  public List<ReservationDto> reservationList(Long memberId, String status) {
    return reservationMapper.reservationList(memberId, status);
  }
}
