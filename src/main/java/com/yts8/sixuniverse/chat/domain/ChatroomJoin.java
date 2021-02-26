package com.yts8.sixuniverse.chat.domain;


import lombok.Data;



@Data

public class ChatroomJoin {
    private int joinNum;
    private int memberId;
    private String name;
    private int chatRef;

    /*테스트용*/
    public ChatroomJoin(int joinNum, int memberId, String name, int chatRef) {
        this.joinNum = joinNum;
        this.memberId = memberId;
        this.name = name;
        this.chatRef = chatRef;
    }
}
