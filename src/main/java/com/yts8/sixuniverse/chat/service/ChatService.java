package com.yts8.sixuniverse.chat.service;

import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;


public interface ChatService {

  /* 채팅메세지 보내기*/
   void saveMessage(ChatDto chatDto);

  /* 채팅방 생성용 메서드*/
  void createChatroomJoin(ChatroomJoinDto chatroomJoinDto);




}
