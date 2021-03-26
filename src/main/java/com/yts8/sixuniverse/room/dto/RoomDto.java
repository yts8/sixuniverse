package com.yts8.sixuniverse.room.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomDto {

  private Long roomId;
  private Long memberId;
  private String zipcode;
  private String address;
  private String subAddress;
  private String buildingType;
  private String roomType;
  private int maxPeople;
  private int bedroomCount;
  private int bedCount;
  private int bathCount;
  private String content;
  private String title;
  private int maxDateNumber;
  private int price;
  private String checkInTime;
  private String checkOutTime;
  private int expiryDate;
  private String status;
  private LocalDateTime createDate;
  private LocalDateTime renewDate;
}
