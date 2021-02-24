package com.yts8.sixuniverse.room.service;

import com.yts8.sixuniverse.room.repository.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

  private final RoomMapper roomMapper;

  @Override
  public void RoomRegi() {
    System.out.println("roomtest");
  }

}
