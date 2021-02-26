package com.yts8.sixuniverse.chat.controller;

import com.yts8.sixuniverse.chat.domain.Chat;
import com.yts8.sixuniverse.chat.domain.ChatroomJoin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/chat")

public class ChatController {

    @GetMapping("")
    public String chat(Model model){

        /*메세지 출력(타임리프테스트)*/
        Chat chat = new Chat(1,2,1,"타임리프내용",LocalDateTime.now());

        ChatroomJoin cRoom = new ChatroomJoin(1,2,"채팅방 이름(기본설정 상대방이름)-타임리프",1);
        model.addAttribute("chat",chat);

        model.addAttribute("cRoom",cRoom);

        return "chat/index";
    }



}
