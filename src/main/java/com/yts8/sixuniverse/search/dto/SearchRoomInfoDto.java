package com.yts8.sixuniverse.search.dto;

import lombok.Data;

@Data
public class SearchRoomInfoDto {
  private Long roomId;
  private String hostGrade;
  private Long roomImg;
  private int reviewScore;
  private int reviewCount;
  private String title;
  private String buildingType;
  private String roomType;
  private String address;
  private String subAddress;
  private int price;
}
