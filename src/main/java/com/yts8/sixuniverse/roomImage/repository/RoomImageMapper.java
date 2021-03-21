package com.yts8.sixuniverse.roomImage.repository;

import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomImageMapper {
  void save(RoomImageDto roomImageDto);

  void updateImage(RoomImageDto roomImageDto);

  List<RoomImageDto> findByRoomId(Long roomId);

  RoomImageDto findOneByRoomId(Long roomId);

}
