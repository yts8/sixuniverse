package com.yts8.sixuniverse.api.chat;

import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.service.ChatService;
import com.yts8.sixuniverse.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class ChatApiController {
  private final ChatService chatService;


  @PostMapping("/api/chat/insert/chat")
  public void insertChat(HttpSession httpSession, HttpServletRequest request) {
    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    /* 채팅창에서 입력한 메세지 */
    String content = request.getParameter("content");
    /* 저장소 생성 */
    ChatDto chatDto = new ChatDto();
    ChatroomJoinDto chatroomJoinDto = new ChatroomJoinDto();
    /* 먼저, 채팅방 참여 데이터 생성 */
    chatroomJoinDto.setMemberId(member.getMemberId());
    chatroomJoinDto.setChatRef(1);
    chatroomJoinDto.setName(member.getUsername()); /* 채팅방 기본 이름은 username*/
    chatService.createChatroomJoin(chatroomJoinDto);
    /* 채팅데이터 생성 */
    chatDto.setMemberId(member.getMemberId());
    chatDto.setJoinNum(chatroomJoinDto.getJoinNum());
    chatDto.setCreateDate(LocalDateTime.now());
    chatDto.setContent(content);
    chatService.addMessage(chatDto);
    /* 해결해야 할 것 -> chatRef값 어떻게 할건지,,*/

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
