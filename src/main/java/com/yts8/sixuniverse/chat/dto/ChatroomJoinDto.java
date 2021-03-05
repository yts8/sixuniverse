package com.yts8.sixuniverse.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class ChatroomJoinDto {
    private int joinNum;
    private int memberId;
    private String name;
    private int chatRef;
}
