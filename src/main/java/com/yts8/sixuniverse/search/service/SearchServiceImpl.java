package com.yts8.sixuniverse.search.service;

import com.yts8.sixuniverse.room.dto.RoomInfoDto;
import com.yts8.sixuniverse.search.dto.SearchDto;
import com.yts8.sixuniverse.search.repository.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchServiceImpl implements SearchService {

  private final SearchMapper searchMapper;

  @Override
  public List<RoomInfoDto> searchList(SearchDto searchDto) {
    return searchMapper.searchList(searchDto);
  }
}
