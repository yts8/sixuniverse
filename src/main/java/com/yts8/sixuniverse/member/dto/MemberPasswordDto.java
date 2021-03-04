package com.yts8.sixuniverse.member.dto;

import lombok.Data;

@Data
public class MemberPasswordDto {
  private String oldPassword;
  private String newPassword;
}
