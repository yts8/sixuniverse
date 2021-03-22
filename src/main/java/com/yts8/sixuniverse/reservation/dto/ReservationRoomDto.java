package com.yts8.sixuniverse.reservation.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ReservationRoomDto {
  private Long reservationId;
  private Long roomId;
  private String status;
  private int adult;
  private int kid;
  private int infant;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate checkIn;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate checkOut;

  private String address;
  private String subAddress;
  private String title;
  private String username;
  private int price;
}
