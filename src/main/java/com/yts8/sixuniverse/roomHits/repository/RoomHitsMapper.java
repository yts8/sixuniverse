package com.yts8.sixuniverse.roomHits.repository;

import com.yts8.sixuniverse.roomHits.dto.RoomHitsDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomHitsMapper {

  void save(RoomHitsDto roomHitsDto);

  RoomHitsDto findByRoomIdAndReadDate(RoomHitsDto roomHitsDto);

  void addHits(Long roomHitsId);

}
