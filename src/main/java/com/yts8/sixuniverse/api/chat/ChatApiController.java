package com.yts8.sixuniverse.api.chat;

import com.yts8.sixuniverse.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class ChatApiController {
  private final ChatService chatService;





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
