package com.yts8.sixuniverse.roomFacility.service;

import com.yts8.sixuniverse.roomFacility.dto.RoomFacilityDto;
import com.yts8.sixuniverse.roomFacility.repository.RoomFacilityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomFacilityServiceImpl implements RoomFacilityService {

  private final RoomFacilityMapper facilityMapper;

  @Override
  public void save(List<RoomFacilityDto> facilityDtos) {
    facilityMapper.removeByRoomIdAndCategoryName(facilityDtos.get(0));
    facilityMapper.save(facilityDtos);
  }

  @Override
  public List<String> findByRoomIdAndCategoryName(RoomFacilityDto roomFacilityDto) {
    return facilityMapper.findByRoomIdAndCategoryName(roomFacilityDto);
  }

  @Override
  public void removeByRoomId(Long roomId) {
    facilityMapper.removeByRoomId(roomId);
  }

}
