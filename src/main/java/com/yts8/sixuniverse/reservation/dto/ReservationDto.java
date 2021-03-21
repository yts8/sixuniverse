package com.yts8.sixuniverse.reservation.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservationDto {
  private Long reservationId;
  private Long roomId;
  private Long memberId;
  private String status;
  private int adult;
  private int kid;
  private int infant;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate checkIn;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate checkOut;

  private LocalDateTime createDate;
  private LocalDateTime cancelDate;
  private Long updateTarget;
}
