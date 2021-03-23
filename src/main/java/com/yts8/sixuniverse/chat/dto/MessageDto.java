package com.yts8.sixuniverse.chat.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
  private String profileImg;
  private String username;
  private LocalDateTime createDate;
  private Long chatRef;
  private String content;
}
