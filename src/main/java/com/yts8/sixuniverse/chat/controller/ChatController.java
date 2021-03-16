package com.yts8.sixuniverse.chat.controller;

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

@Controller
@RequestMapping("")
@RequiredArgsConstructor


/*roomID로 숙소테이블 찾아서 거기에 있는 호스트ID찾기*/


public class ChatController {

  private final ChatroomJoinService chatroomJoinService;

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



  /* 연습연습 */
  @GetMapping("/chatTest/{hostId}")
  public Long Test(HttpSession httpSession, Model model, @PathVariable Long hostId) {
    MemberDto member = (MemberDto) httpSession.getAttribute("member");

    Long hostChatRef = chatroomJoinService.getHostChatRef(hostId);
    Long myChatRef = chatroomJoinService.getMyChatRef(member.getMemberId());
    Long realChatRef = 0L;
    if(hostChatRef ==0L && myChatRef == 0L){ // 둘 다 값이 없으면 새로운 chatRef를 생성한다.
      realChatRef++;
      return realChatRef;
    }else{ // 있으면 비교한다
      if(hostChatRef == myChatRef){ // 같으면 리턴
        return realChatRef;
      }else { // 같지 않다면
        realChatRef++; // 생성
        return realChatRef;
      }
    //   chatroomJoinService.createNewRoom(realChatRef);
    }
    model.addAttribute("realChatRef",realChatRef);

    return "redirect:/chatTest/"+ realChatRef;
  }







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
