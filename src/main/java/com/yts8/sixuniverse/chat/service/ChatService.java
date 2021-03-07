package com.yts8.sixuniverse.chat.service;

import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;


public interface ChatService {

  /* 채팅메세지 보내기*/
  void addMessage(ChatDto chatDto);

  /* 채팅방 생성용 메서드*/
  void createChatroomJoin(ChatroomJoinDto chatroomJoinDto);

  /* 채팅방 존재 여부*/
  // 내 memberId로 chat테이블의 content가 있는지 확인
  // 같은 chat_ref를 참조하는
  ChatDto findMessage(Long memberId);
}
