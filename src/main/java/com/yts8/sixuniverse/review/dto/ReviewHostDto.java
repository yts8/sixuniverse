package com.yts8.sixuniverse.review.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewHostDto {
  private String guestName;
  private String guestProfileImg;
  private LocalDate checkIn;
  private LocalDate checkOut;
  private String title;
  private LocalDate reviewRegDate;
  private String reviewContent;
  private int score;
}
