package com.yts8.sixuniverse.roomImage.service;

import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;
import com.yts8.sixuniverse.roomImage.repository.RoomImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class roomImageServiceImpl implements RoomImageService {

  private final RoomImageMapper roomImageMapper;

  @Override
  public void save(List<RoomImageDto> imageDto) {
    roomImageMapper.save(imageDto);
  }

  @Override
  public List<RoomImageDto> findByRoomId(RoomImageDto roomImageDto) {
    return roomImageMapper.findByRoomId(roomImageDto);
  }

}
