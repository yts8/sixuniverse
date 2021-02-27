package com.yts8.sixuniverse.reservation.dto;

import lombok.Data;

import java.sql.Date;
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
  private Date checkIn;
  private Date checkOut;
  private LocalDateTime createDate;
  private LocalDateTime cancelDate;
}
