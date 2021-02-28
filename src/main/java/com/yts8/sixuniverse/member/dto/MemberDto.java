package com.yts8.sixuniverse.member.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {
  private int memberId;
  private String email;
  private String password;
  private String username;
  private String birthdate;
  private String profileImg;
  private String zipcode;
  private String address;
  private String subAddress;
  private String mobile;
  private String bio;
  private int mileage;
  private String hostGrade;
  private String guestGrade;
  private String social;
  private String role;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;
  private LocalDateTime deleteDate;
}
