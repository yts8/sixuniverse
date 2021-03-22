package com.yts8.sixuniverse.chat.controller;

import com.yts8.sixuniverse.chat.dto.*;
import com.yts8.sixuniverse.chat.service.ChatService;
import com.yts8.sixuniverse.chat.service.ChatroomJoinService;
import com.yts8.sixuniverse.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor

public class ChatController {

  private final ChatroomJoinService chatroomJoinService;
  private final ChatService chatService;


  @GetMapping("/chat/{hostId}")
  public String Test(HttpSession httpSession, @PathVariable Long hostId, Model model) {

    MemberDto member = (MemberDto) httpSession.getAttribute("member");


    MemberIdDto memberIdDto = new MemberIdDto();
    memberIdDto.setMyMemberId(member.getMemberId());
    memberIdDto.setHostId(hostId);

    /* 공통으로 가지고 있는 chatRef count */
    Long chatRef = chatroomJoinService.chatRefCount(memberIdDto);

    if (chatRef == 0) { // 값이 없으면 새로운 채팅방 생성
      chatRef = chatroomJoinService.createNewChatRef();

      /* 채팅방 생성 */
      /* 게스트 채팅방 번호*/
      ChatroomJoinDto chatroomJoinDto = new ChatroomJoinDto();
      chatroomJoinDto.setName("호스트이름");
      chatroomJoinDto.setMemberId(member.getMemberId());
      chatroomJoinDto.setChatRef(chatRef);

      /* 호스트 채팅방 번호*/
      ChatroomJoinDto chatroomJoinDto2 = new ChatroomJoinDto();
      chatroomJoinDto2.setName("게스트이름");
      chatroomJoinDto2.setMemberId(hostId);
      chatroomJoinDto2.setChatRef(chatRef);

      /* 채팅방 저장 */
      chatroomJoinService.testCreateNewRoom(chatroomJoinDto);
      chatroomJoinService.testCreateNewRoom(chatroomJoinDto2);
    } else if (chatRef != 0) {
      chatRef = chatroomJoinService.getChatRefTest(memberIdDto);
    }


    model.addAttribute("hostId", hostId);
    return "redirect:/chat/host/{hostId}/chatroom/" + chatRef;
  }

  @GetMapping("/chat/host/{hostId}/chatroom/{chatRef}")
  public String chatTest(@PathVariable Long chatRef, @PathVariable Long hostId, Model model, HttpSession httpSession) {

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    Long myMemberId = member.getMemberId();
    Long chatMessages = chatService.findMessages(chatRef);

    if (chatMessages != 0) {
      List<ChatDto> chatDto = chatService.findMessageByChatRef(chatRef);
      List<MessageDto> lastChatDto = chatService.getLastChat(myMemberId);
      model.addAttribute("chatDto", chatDto);
    }

    model.addAttribute("hostId", hostId);
    model.addAttribute("chatRef", chatRef);

    return "chat/index";

  }

}
