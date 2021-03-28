package com.yts8.sixuniverse.review.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
  private LocalDateTime reviewRegDate;
  private String replyContent;
  private LocalDateTime replyRegDate;
  private int score;
}
