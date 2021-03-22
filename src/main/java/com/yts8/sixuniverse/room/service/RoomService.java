package com.yts8.sixuniverse.room.service;

import com.yts8.sixuniverse.room.dto.RoomDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {

  RoomDto findById(Long roomId);

  List<RoomDto> findByMemberId(Long memberId);

  LocalDateTime findByRenewDate(Long roomId);

  int findByExpiryDate(Long roomId);

  void save(RoomDto roomDto);

  void updateAddress(RoomDto roomDto);

  void updateTypes(RoomDto roomDto);

  void updateBedrooms(RoomDto roomDto);

  void updateInfo(RoomDto roomDto);

  void updateAvailabilitySettings(RoomDto roomDto);

  void updatePrice(RoomDto roomDto);

  void updateStatus(RoomDto roomDto);

  void updateRenew(RoomDto roomDto);

  void updateExpiry(LocalDateTime renewDate);

  void remove(Long roomId);

}
