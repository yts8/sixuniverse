package com.yts8.sixuniverse.reservation.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class HostReservationDto {
  private String status;
  private Long roomId;
  private String title;
  private int price;
  private Long reservationId;
  private Long memberId;
  private int adult;
  private int kid;
  private int infant;
  private int commission;
  private String username;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate checkIn;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate checkOut;

  @DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm")
  private LocalDateTime createDate;
}
