package com.yts8.sixuniverse.room.dto;

import lombok.Data;

@Data
public class RoomInfoDto {

  private Long roomId;
  private String hostGrade;
  private String roomImg;
  private double reviewScore;
  private int reviewCount;
  private String title;
  private String buildingType;
  private String roomType;
  private String address;
  private String subAddress;
  private int price;

}

