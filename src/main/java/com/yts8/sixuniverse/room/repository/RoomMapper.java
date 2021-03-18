package com.yts8.sixuniverse.room.repository;

import com.yts8.sixuniverse.room.dto.RoomDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface RoomMapper {

  RoomDto findById(Long roomId);

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
}
