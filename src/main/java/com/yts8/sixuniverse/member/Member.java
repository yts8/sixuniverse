package com.yts8.sixuniverse.member;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
  private int member_id;
  private String email;
  private String password;
  private String name;
  private String birthdate;
  private String profileImg;
  private String zipcode;
  private String address;
  private String subAddress;
  private String phone;
  private String bio;
  private int mileage;
  private String hostGrade;
  private String guestGrade;
  private String social;
  private String role;
  private LocalDateTime createDate;
  private LocalDateTime deleteDate;
}
