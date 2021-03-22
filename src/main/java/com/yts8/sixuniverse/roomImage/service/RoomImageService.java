package com.yts8.sixuniverse.roomImage.service;

import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;

import java.util.List;

public interface RoomImageService {

  void save(RoomImageDto roomImageDto);

  void updateImage(RoomImageDto roomImageDto);

  List<RoomImageDto> findByRoomId(Long roomId);

  RoomImageDto findOneByRoomId(Long roomId);

  void removeByRoomId(Long roomId);

}
