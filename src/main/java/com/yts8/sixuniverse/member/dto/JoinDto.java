package com.yts8.sixuniverse.member.dto;

import lombok.Data;

@Data
public class JoinDto {

  private String email;
  private String password;
  private String username;
  private String birthdate;
  private String mobile;
}
