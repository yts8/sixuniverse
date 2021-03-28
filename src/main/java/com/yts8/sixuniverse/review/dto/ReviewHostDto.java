package com.yts8.sixuniverse.review.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewHostDto {
  private String guestId;
  private String guestName;
  private String guestProfileImg;
  private LocalDate checkIn;
  private LocalDate checkOut;
  private String title;
  private Long reviewId;
  private String reviewContent;
  private LocalDate reviewRegDate;
  private String replyContent;
  private LocalDate replyRegDate;
  private int score;
}
