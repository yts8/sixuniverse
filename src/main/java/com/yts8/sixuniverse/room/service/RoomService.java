package com.yts8.sixuniverse.room.service;

import com.yts8.sixuniverse.room.dto.RoomDto;

import java.util.Date;

public interface RoomService {

  RoomDto findById(Long roomId);

  Long findByMemberId(Long memberId);

  Date findByCreateDate(Long roomId);

  void save(RoomDto roomDto);

  void updateTypes(RoomDto roomDto);

  void updateBedrooms(RoomDto roomDto);

  void updateInfo(RoomDto roomDto);

  void updateAvailabilitySettings(RoomDto roomDto);

  void updatePrice(RoomDto roomDto);
}
