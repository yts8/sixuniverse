package com.yts8.sixuniverse.roomHits.service;

import com.yts8.sixuniverse.roomHits.dto.RoomHitsDto;

public interface RoomHitsService {

  void save(RoomHitsDto roomHitsDto);

  RoomHitsDto findByRoomIdAndReadDate(RoomHitsDto roomHitsDto);

  void addHits(Long roomHitsId);

}
