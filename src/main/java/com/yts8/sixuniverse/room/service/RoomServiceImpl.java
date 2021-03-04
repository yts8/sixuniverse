package com.yts8.sixuniverse.room.service;

import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.repository.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

  private final RoomMapper roomMapper;

  @Override
  public RoomDto findById(Long roomId) {
    return roomMapper.findById(roomId);
  }

  @Override
  public void save(RoomDto roomDto) {
    roomMapper.save(roomDto);
  }

  @Override
  public void updateTypes(RoomDto roomDto) {
    roomMapper.updateTypes(roomDto);
  }

  @Override
  public void updateBedrooms(RoomDto roomDto) {
    roomMapper.updateBedrooms(roomDto);
  }

  @Override
  public void updateInfo(RoomDto roomDto) {
    roomMapper.updateInfo(roomDto);
  }

  @Override
  public void updateAvailabilitySettings(RoomDto roomDto) {
    roomMapper.updateAvailabilitySettings(roomDto);
  }

  @Override
  public void updatePrice(RoomDto roomDto) {
    roomMapper.updatePrice(roomDto);
  }
}
