package com.yts8.sixuniverse.roomFacility.repository;

import com.yts8.sixuniverse.roomFacility.dto.RoomFacilityDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomFacilityMapper {
  void save(List<RoomFacilityDto> facilityDtos);

  List<RoomFacilityDto> findByRoomIdAndCategoryName(RoomFacilityDto roomFacilityDto);
}
