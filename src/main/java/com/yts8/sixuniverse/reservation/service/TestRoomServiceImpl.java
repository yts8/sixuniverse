package com.yts8.sixuniverse.reservation.service;

import com.yts8.sixuniverse.reservation.repository.TestRoomMapper;
import com.yts8.sixuniverse.room.dto.RoomDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestRoomServiceImpl implements TestRoomService {

  private final TestRoomMapper testRoomMapper;
  private final SqlSession sqlSession;

  private final static String namespace="com.yts8.sixuniverse.reservation.repository.TestRoomMapper";

  @Override
  public List<RoomDto> roomList() {
    return sqlSession.selectList(namespace+".selectRoom");
  }
}
