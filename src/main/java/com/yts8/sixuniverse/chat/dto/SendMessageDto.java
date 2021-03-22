package com.yts8.sixuniverse.chat.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SendrMessageDto {
  private String msg;
  private Long memberId;
  private String profileImg;
  private LocalDateTime date;
  private String username;
  private Long chatRef;
}
