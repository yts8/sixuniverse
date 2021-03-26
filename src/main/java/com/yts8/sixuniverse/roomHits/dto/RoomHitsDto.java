package com.yts8.sixuniverse.roomHits.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomHitsDto {

  private Long roomHitsId;
  private Long roomId;
  private LocalDate readDate;
  private int hits;
}
