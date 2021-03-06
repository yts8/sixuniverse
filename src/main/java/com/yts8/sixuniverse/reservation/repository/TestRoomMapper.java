package com.yts8.sixuniverse.reservation.repository;

import com.yts8.sixuniverse.room.dto.RoomDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestRoomMapper {
  List<RoomDto> selectRoom();
}
