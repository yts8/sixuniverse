package com.yts8.sixuniverse.chat.dto;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ChatDto {
  private Long chatId;
  private Long memberId;
  private Long joinNum;
  private String content;
  private LocalDateTime createDate;


}
