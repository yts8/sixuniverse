package com.yts8.sixuniverse.chat.controller;

import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("")
@RequiredArgsConstructor

public class ChatController {
  @GetMapping("/chatTest/{chatroomId}")
  public String chat(HttpSession httpSession, Model model, @PathVariable int chatroomId, ChatDto chatDto) {

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    String userName = member.getUsername();
    String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM월 dd일 HH:MM"));
    model.addAttribute("userName", userName);
    model.addAttribute("chatroomId", chatroomId);
    model.addAttribute("date",date);
    return "chat/index";
  }
}
