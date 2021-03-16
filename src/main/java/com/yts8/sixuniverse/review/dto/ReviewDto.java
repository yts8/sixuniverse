package com.yts8.sixuniverse.review.dto;

import lombok.Data;

import java.time.LocalDate;

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
  private LocalDate reviewRegDate;
  private String replyContent;
  private LocalDate replyRegDate;
}