package com.yts8.sixuniverse.search.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchDto {
  private String location;
  private LocalDate checkIn;
  private LocalDate checkOut;
  private int totalGuest;
}
