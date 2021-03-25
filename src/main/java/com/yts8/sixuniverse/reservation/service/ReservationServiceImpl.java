package com.yts8.sixuniverse.reservation.service;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.dto.HostReservationDto;
import com.yts8.sixuniverse.reservation.dto.ReservationRoomPaymentDto;
import com.yts8.sixuniverse.reservation.repository.ReservationMapper;
import com.yts8.sixuniverse.review.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
  public List<ReservationDto> reservationList(ReservationDto reservationDto) {
    return reservationMapper.reservationList(reservationDto);
  }

  @Override
  public List<HostReservationDto> hostReservationList(ReservationDto reservationDto) {
    return reservationMapper.hostReservationList(reservationDto);
  }

  @Override
  public void reservationCheckOut(LocalDate today) {
    reservationMapper.reservationCheckOut(today);
  }

  @Override
  public void guestReservationUpdateRequest(Long reservationId) {
    reservationMapper.guestReservationUpdateRequest(reservationId);
  }

  @Override
  public void guestReservationUpdateInsert(ReservationDto reservationDto) {
    reservationMapper.guestReservationUpdateInsert(reservationDto);
  }

  @Override
  public void guestReservationCancel(ReservationDto reservationDto) {
    reservationMapper.guestReservationCancel(reservationDto);
  }

  @Override
  public ReviewDto findByRoomIdAndMemberId(ReservationDto reservationDto) {
    return reservationMapper.findByRoomIdAndMemberId(reservationDto);
  }

  @Override
  public List<ReservationRoomPaymentDto> findByUpdateReservationId(Long reservationId) {
    return reservationMapper.findByUpdateReservationId(reservationId);
  }

  @Override
  public ReservationRoomPaymentDto findByCancelReservationId(Long reservationId) {
    return reservationMapper.findByCancelReservationId(reservationId);
  }

  @Override
  public void hostUpdate(ReservationDto reservationDto) {
    reservationMapper.hostUpdate(reservationDto);
  }

  @Override
  public void hostUpdateNo(Long reservationId) {
    reservationMapper.hostUpdateNo(reservationId);
  }


}
