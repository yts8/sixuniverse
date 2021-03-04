package com.yts8.sixuniverse.roomImage.service;

import com.yts8.sixuniverse.roomFacility.dto.RoomFacilityDto;
import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;

import java.util.List;

public interface RoomImageService {

  void save(List<RoomImageDto> imageDtos);

  List<RoomImageDto> findByRoomId(RoomImageDto imageDto);

}
