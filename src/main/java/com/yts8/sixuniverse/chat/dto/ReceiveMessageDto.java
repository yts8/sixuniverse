package com.yts8.sixuniverse.chat.dto;


import lombok.Data;

@Data
public class ReceiveMessageDto {
  private Long memberId;
  private Long chatRef;
  private String msg;
}
