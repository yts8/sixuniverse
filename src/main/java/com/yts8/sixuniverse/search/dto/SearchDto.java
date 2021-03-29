package com.yts8.sixuniverse.search.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class SearchDto {
  private String location;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate checkIn;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate checkOut;
  private int totalGuest;
}
