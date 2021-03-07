package com.yts8.sixuniverse.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
  private int chatId;
  private Long memberId;
  private int joinNum;
  private String content;
  private LocalDateTime createDate;

}
