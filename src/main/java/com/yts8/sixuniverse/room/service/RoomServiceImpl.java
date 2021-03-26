package com.yts8.sixuniverse.room.service;

import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.dto.RoomInfoDto;
import com.yts8.sixuniverse.room.repository.RoomMapper;
import com.yts8.sixuniverse.roomFacility.service.RoomFacilityService;
import com.yts8.sixuniverse.roomImage.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

  private final RoomMapper roomMapper;
  private final RoomFacilityService roomFacilityService;
  private final RoomImageService roomImageService;

  @Override
  public RoomDto findById(Long roomId) {
    return roomMapper.findById(roomId);
  }

  @Override
  public List<RoomDto> findByMemberId(Long memberId) {
    return roomMapper.findByMemberId(memberId);
  }

  @Override
  public LocalDateTime findByRenewDate(Long roomId) {
    return roomMapper.findByRenewDate(roomId);
  }

  @Override
  public int findByExpiryDate(Long roomId) {
    return roomMapper.findByExpiryDate(roomId);
  }

  @Override
  public List<RoomInfoDto> roomInfoFindByMemberId(Long memberId) {
    return roomMapper.roomInfoFindByMemberId(memberId);
  }

  @Override
  public void save(RoomDto roomDto) {
    roomMapper.save(roomDto);
  }

  public void updateAddress(RoomDto roomDto) {
    roomMapper.updateAddress(roomDto);
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

  public void updateStatus(RoomDto roomDto) {
    roomMapper.updateStatus(roomDto);
  }

  @Override
  public void updateRenew(RoomDto roomDto) {
    roomMapper.updateRenew(roomDto);
  }

  @Override
  public void updateExpiry(LocalDateTime renewDate) {
    roomMapper.updateExpiry(renewDate);
  }

  @Override
  public void remove(Long roomId) {
    roomMapper.remove(roomId);
    roomFacilityService.removeByRoomId(roomId);
    roomImageService.removeByRoomId(roomId);
  }
}
