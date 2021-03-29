package com.yts8.sixuniverse.search.service;

import com.yts8.sixuniverse.room.dto.RoomInfoDto;
import com.yts8.sixuniverse.search.dto.SearchDto;

import java.util.List;

public interface SearchService {

  List<RoomInfoDto> searchList(SearchDto searchDto);

}
