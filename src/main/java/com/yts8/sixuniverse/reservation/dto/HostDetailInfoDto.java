package com.yts8.sixuniverse.reservation.dto;

import lombok.Data;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class HostDetailInfoDto {
  private String username;
  private String profileImg;
  private String status;
  private int dateSub;
  private int price;
  private String title;
  private int scoreAll;
  private String reviewContent;
  private String address;
  private String hostGrade;
  private String mobile;
  private int adult;
  private int kid;
  private int infant;
  private DateTime checkIn;
  private DateTime checkOut;
  private LocalDateTime createDate;
  private Long guestMemberId;
  private Long hostMemberId;
  private Long reservationId;
  private int commission;
  private int totalPrice;
  private int mileage;
}
