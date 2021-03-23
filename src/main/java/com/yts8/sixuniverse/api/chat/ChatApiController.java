package com.yts8.sixuniverse.api.chat;

import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.dto.MessageDto;
import com.yts8.sixuniverse.chat.service.ChatService;
import com.yts8.sixuniverse.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatApiController {
  private final ChatService chatService;

  /* 조지 테스트 */
  @PostMapping("/{myMemberId}")
  public List<MessageDto> getChatroom(@PathVariable Long myMemberId) {
    List<MessageDto> chatDto = chatService.getLastChat(myMemberId);


    return chatDto;

  }
}

