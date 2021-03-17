package com.yts8.sixuniverse.chat.controller;

import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor


/*roomID로 숙소테이블 찾아서 거기에 있는 호스트ID찾기*/


public class ChatController {

  private final ChatroomJoinService chatroomJoinService;

/* 원래꺼
  @GetMapping("/chatTest/{chatroomId}")
  public String chat(HttpSession httpSession, Model model, @PathVariable int chatroomId) {

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    String userName = member.getUsername();
    String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM월 dd일 HH:MM"));
    model.addAttribute("userName", userName);
    model.addAttribute("chatroomId", chatroomId);
    model.addAttribute("date",date);
    return "chat/index";
  }
*/


  /* 연습연습 */
  @GetMapping("/chatTest/{hostId}")
  public String Test(HttpSession httpSession, @PathVariable Long hostId) {

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    Long myMemberId = member.getMemberId();
    System.out.println("chatController");
    System.out.println("내 memberId : " + myMemberId);

   /* Long chatRef = null;
    if (chatRef==null) { // null 이면 (공통된 id가 없으면)
      // 기존의 chat_ref의 최댓값에서 +1해서 리턴
      chatRef = chatroomJoinService.createNewChatRef();

    }else if(chatRef!=null){
      chatRef = chatroomJoinService.getChatRef(myMemberId, hostId);
    }
    System.out.println("chatController의 chatRef : " + chatRef);
    System.out.println("내 memberId : " + myMemberId);*/

    List<Long> hostChatRef = chatroomJoinService.getHostChatRef(hostId);
    List<Long> myChatRef = chatroomJoinService.getMyChatRef(myMemberId);


    Long chatRef = null;
    for(int i=0; i<hostChatRef.size(); i++){
      if(myChatRef.contains(myChatRef.get(i))){
        chatRef = (Long) hostChatRef.get(i);
      }else{
       chatRef = chatroomJoinService.createNewChatRef();
      }
    }
    System.out.println("chatController의 chatRef : " + chatRef);





    return "redirect:/chatTest/Test/"+chatRef;
  }
  @GetMapping("/chatTest/Test/{chatRef}")
  public String chatTest(@PathVariable Long chatRef, Model model,HttpSession httpSession){
    System.out.println("채팅방 입장 !!! 채팅방번호 ; " + chatRef);

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    String name = member.getUsername();
    String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM월 dd일 HH:MM"));
    model.addAttribute("name", name);
    model.addAttribute("chatRef",chatRef);
    model.addAttribute("date",date);

    /* 채팅방 생성 */

    ChatroomJoinDto chatroomJoinDto = new ChatroomJoinDto();
    chatroomJoinDto.setName(member.getUsername());
    chatroomJoinDto.setMemberId(member.getMemberId());
    chatroomJoinDto.setChatRef(chatRef);
    chatroomJoinService.testCreateNewRoom(chatroomJoinDto);

    model.addAttribute("joinNum",chatroomJoinDto.getJoinNum());

    System.out.println("채팅방 완성!!");
    return "chat/index";

  }


/*
  @GetMapping("/chatTest/{chatRef}")
  public String chat(HttpSession httpSession, Model model, @PathVariable Long chatRef) {

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    String userName = member.getUsername();
    String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM월 dd일 HH:MM"));
    model.addAttribute("userName", userName);
    model.addAttribute("chatRef", chatRef);
    model.addAttribute("date",date);
    return "chat/index";
  }
*/








  /*@GetMapping("/chatTest/{chatroomId}")
  public String chat(HttpSession httpSession, Model model, @PathVariable int chatroomId) {

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    String userName = member.getUsername();
    String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM월 dd일 HH:MM"));
    model.addAttribute("userName", userName);
    model.addAttribute("memberId", chatroomId);
    model.addAttribute("date",date);
    return "chat/index";
  }*/


}
