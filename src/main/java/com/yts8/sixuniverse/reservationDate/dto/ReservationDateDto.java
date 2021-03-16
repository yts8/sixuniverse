package com.yts8.sixuniverse.reservationDate.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservationDateDto {

  private Long reservationDateId;
  private Long reservationId;
  private Long roomId;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate reservationDate;

}
