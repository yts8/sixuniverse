package com.yts8.sixuniverse.search.service;

import com.yts8.sixuniverse.search.dto.SearchDto;
import com.yts8.sixuniverse.search.dto.SearchRoomInfoDto;

import java.util.List;

public interface SearchService {

  List<SearchRoomInfoDto> searchList(SearchDto searchDto);

}
