package com.yts8.sixuniverse.review.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReviewGuestDto {
  private Long memberId;
  private String hostName;
  private String hostProfileImg;
  private Long reviewId;
  private String reviewContent;
  private LocalDateTime reviewRegDate;
  private String replyContent;
  private LocalDateTime replyRegDate;
  private Long reservationId;
  private String status;
  private LocalDate checkOut;
  private LocalDateTime cancelDate;
}
