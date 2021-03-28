package com.yts8.sixuniverse.reservation.repository;

import com.yts8.sixuniverse.reservation.dto.HostDetailInfoDto;
import com.yts8.sixuniverse.reservation.dto.HostReservationDto;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HostReservationMapper {

  List<HostReservationDto> hostReservationList(ReservationDto reservationDto);

  HostDetailInfoDto HostDetailInfo(Long reservationId);

}
