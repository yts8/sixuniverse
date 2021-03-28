package com.yts8.sixuniverse.review.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReviewDto {
  private Long reviewId;
  private Long roomId;
  private Long reservationId;
  private int scoreAll;
  private int scoreClean;
  private int scoreLocation;
  private int scoreService;
  private String reviewContent;
  private LocalDateTime reviewRegDate;
  private String replyContent;
  private LocalDateTime replyRegDate;
}