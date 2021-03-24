package com.yts8.sixuniverse.roomHits.service;

import com.yts8.sixuniverse.roomHits.dto.RoomHitsDto;
import com.yts8.sixuniverse.roomHits.repository.RoomHitsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomHitsServiceImpl implements RoomHitsService {

  private final RoomHitsMapper roomHitsMapper;


  @Override
  public void save(RoomHitsDto roomHitsDto) {
    roomHitsMapper.save(roomHitsDto);
  }

  @Override
  public RoomHitsDto findByRoomIdAndReadDate(RoomHitsDto roomHitsDto) {
    RoomHitsDto byRoomIdAndReadDate = roomHitsMapper.findByRoomIdAndReadDate(roomHitsDto);
    System.out.println("roomHitsDto = " + roomHitsDto);
    System.out.println("byRoomIdAndReadDate = " + byRoomIdAndReadDate);

    return byRoomIdAndReadDate;
  }

  @Override
  public void updateHits(Long roomHitsId) {
    roomHitsMapper.updateHits(roomHitsId);
  }
}
