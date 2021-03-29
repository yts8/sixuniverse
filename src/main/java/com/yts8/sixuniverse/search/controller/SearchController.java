package com.yts8.sixuniverse.search.controller;

import com.yts8.sixuniverse.search.dto.SearchDto;
import com.yts8.sixuniverse.search.dto.SearchRoomInfoDto;
import com.yts8.sixuniverse.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

  private final SearchService searchService;

  @GetMapping("/")
  public String search(Model model, SearchDto searchDto) {

    SearchRoomInfoDto searchRoomInfoDto = new SearchRoomInfoDto();
    List<SearchRoomInfoDto> searchList = searchService.searchList(searchDto);

    model.addAttribute("searchList", searchList);

    model.addAttribute("title", "검색");
    return "search/list";
  }

}
