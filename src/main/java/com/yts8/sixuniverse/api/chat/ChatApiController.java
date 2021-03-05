package com.yts8.sixuniverse.api.chat;

import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatApiController {
  private final ChatService chatService;

  @PostMapping("/insertChat")
public void insertChat(HttpSession httpSession, ChatDto chatDto, ChatroomJoinDto chatroomJoinDto){
/*  MemberDto member = (MemberDto) httpSession.getAttribute("member");*/
  chatDto.setMemberId(1);
  chatDto.setCreateDate(LocalDateTime.now());
  chatDto.setJoinNum(1);
  chatDto.setContent(chatDto.getContent());

  chatroomJoinDto.setName("테스트");
  chatroomJoinDto.setChatRef(1);
  chatService.insertChat(chatDto);
  chatService.createChatroomJoin(chatroomJoinDto);

  }
}



/* 조지 테스트
  @GetMapping("/chatroom/{chatId}")
  public List<ChatDto> getChatroom(@PathVariable int chatId) {*/
   /* List<ChatDto> chatDtos = new ArrayList<>();

    ChatDto chatDto1 = new ChatDto();
    ChatDto chatDto2 = new ChatDto();
    chatDto1.setChatId(1);
    chatDto1.setContent("dto1");
    chatDto2.setChatId(2);
    chatDto2.setContent("dto2");

    chatDtos.add(chatDto1);
    chatDtos.add(chatDto2);*/

  /*  return chatDtos;*/
/*
  }
}
*/
