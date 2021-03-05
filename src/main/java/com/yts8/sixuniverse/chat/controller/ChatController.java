package com.yts8.sixuniverse.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor

public class ChatController {
/*  private final ChatService chatService;*/

    @GetMapping("")
    public String chat(){
/*        *//*메세지 출력(타임리프테스트)*//*
        ChatDto chat1 = new ChatDto(1,2,1,"타임리프내용",LocalDateTime.now());
        ChatDto chat2 = new ChatDto(2,3,2,"타임리프내용2",LocalDateTime.now());


        // 생성된 데이터를 List에 담는다.
        List<ChatDto> chat = new ArrayList<>();
        chat.add(chat1);
        chat.add(chat2);
        model.addAttribute("chatRoomList",chat);

        List<ChatroomJoinDto> chatroomJoinDtoList = new ArrayList<>();
       ChatroomJoinDto cRoom = new ChatroomJoinDto(1,2,"채팅방 이름(기본설정 상대방이름)-타임리프",1);
       ChatroomJoinDto cRoom2 = new ChatroomJoinDto(2,3,"채팅방 이름(기본설정 상대방이름)-타임리프",2);
       chatroomJoinDtoList.add(cRoom);
       chatroomJoinDtoList.add(cRoom2);

        model.addAttribute("chatroomJoinDtoList",chatroomJoinDtoList);*/

        return "chat/index";
    }
/*    @GetMapping("/test")
        public String chatTest(HttpServletRequest request, Model model){

        String test = request.getParameter("test");

        System.out.println(test);

        return "chat/index";
    }*/
/*

  @PostMapping("/message")
  public String chat(ChatDto message){

    chatService.insertChat(message);

    return "chat/index";
  }
*/







}
