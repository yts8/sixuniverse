package com.yts8.sixuniverse.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat/*")

public class ChatController {

    @GetMapping("/chat")
    public String chat(Model model){

        model.addAttribute("chat","메세지(타임리프 테스트)");


        return "chat/chat";
    }
}
