package com.yts8.sixuniverse.reservation.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDateDto {

  private Long reservationDateId;
  private Long reservationId;
  private Long roomId;
  private Date reservationDate;

}
