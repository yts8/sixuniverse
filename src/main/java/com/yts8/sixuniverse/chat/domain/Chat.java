package com.yts8.sixuniverse.chat.domain;


import lombok.Data;

/*테스트용*/
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data


public class Chat {
    private int chatId;
    private int memberId;
    private int joinNum;
    private String content;
    private LocalDateTime createDate;

    /*타임리프 테스트용*/
    public Chat(int chatId, int memberId, int joinNum, String content, LocalDateTime createDate) {
        this.chatId = chatId;
        this.memberId = memberId;
        this.joinNum = joinNum;
        this.content = content;
        this.createDate = createDate;
    }
}
