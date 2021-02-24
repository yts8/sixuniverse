package com.yts8.sixuniverse.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat/*")

public class ChatController {

    @GetMapping("/chat")
    public String chat(){
        return "chat/chat";
    }
}
