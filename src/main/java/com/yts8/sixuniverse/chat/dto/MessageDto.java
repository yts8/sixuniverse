package com.yts8.sixuniverse.chat.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
  private String profileImg;
  private String username;
  private Long memberId;
  private String content;
  private LocalDateTime createDate;
}
