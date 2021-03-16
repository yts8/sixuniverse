package com.yts8.sixuniverse.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatroomJoinDto {
    private Long joinNum;
    private Long memberId;
    private String name;
    private Long chatRef;



}
