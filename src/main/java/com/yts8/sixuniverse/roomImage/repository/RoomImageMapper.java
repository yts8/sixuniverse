package com.yts8.sixuniverse.roomImage.repository;

import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomImageMapper {
  void save(List<RoomImageDto> imageDto);

  List<RoomImageDto> findByRoomId(RoomImageDto imageDto);

}
