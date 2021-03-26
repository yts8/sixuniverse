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
import java.time.LocalDateTime;
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
      chatroomJoinDto.setName(chatService.findUsernameById(hostId));
      chatroomJoinDto.setMemberId(member.getMemberId());
      chatroomJoinDto.setChatRef(chatRef);

      /* 호스트 채팅방 번호*/
      ChatroomJoinDto chatroomJoinDto2 = new ChatroomJoinDto();
      chatroomJoinDto2.setName(member.getUsername());
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

    /* 채팅방 이름 출력 */
    ChatListDto chatListDto = new ChatListDto();
    chatListDto.setChatRef(chatRef);
    chatListDto.setMyMemberId(myMemberId);

    String otherName = chatService.findOtherName(chatListDto);

    String hostName = chatService.findUsernameById(hostId);


    if (chatMessages != 0) {
      List<ChatDto> chatDto = chatService.findMessageByChatRef(chatRef);

      hostId = chatService.findHostId(chatListDto);

      List<MessageDto> lastChatDto = chatService.getLastChat(myMemberId);

      Long countReply = 0L;
      Long countHostRoom = 0L;
      /* 만약 호스트의 메세지 기록이 없을 때*/
      if (countReply == null && countHostRoom == null) {
        countReply = 0L;
        countHostRoom = 0L;
      } else {
        countReply = chatService.countReplyOfHost(hostId);
        countHostRoom = chatService.countHostRoom(hostId);
        double reply = ((double) countReply / countHostRoom) * 100;
        model.addAttribute("reply", reply);
      }

      model.addAttribute("hostId", hostId);
      model.addAttribute("hostName", hostName);
      model.addAttribute("chatDto", chatDto);
      model.addAttribute("lastChat", lastChatDto);
      model.addAttribute("otherName", otherName);
    }
    model.addAttribute("hostId", hostId);
    model.addAttribute("chatRef", chatRef);
    model.addAttribute("otherName", otherName);
    model.addAttribute("hostName", hostName);


    return "chat/index";
  }
}
