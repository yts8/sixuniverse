package com.yts8.sixuniverse.reservation.service;

import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.repository.ReservationMapper;

public interface ReservationService {


  void reservationInsert(ReservationDto reservationDto);
}
