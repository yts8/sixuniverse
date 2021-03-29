package com.yts8.sixuniverse.search.repository;

import com.yts8.sixuniverse.search.dto.SearchDto;
import com.yts8.sixuniverse.search.dto.SearchRoomInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

  List<SearchRoomInfoDto> searchList(SearchDto searchDto);

}
