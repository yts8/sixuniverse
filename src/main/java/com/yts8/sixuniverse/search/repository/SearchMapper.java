package com.yts8.sixuniverse.search.repository;

import com.yts8.sixuniverse.room.dto.RoomInfoDto;
import com.yts8.sixuniverse.search.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

  List<RoomInfoDto> searchList(SearchDto searchDto);

}
