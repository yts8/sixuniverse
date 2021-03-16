package com.yts8.sixuniverse.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
  private Long chatId;
  private Long memberId;
  private Long joinNum;
  private String content;
  private LocalDateTime createDate;

}
