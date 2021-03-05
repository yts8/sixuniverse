package com.yts8.sixuniverse.chat.service;

import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;


public interface ChatService {
  void insertChat(ChatDto chatDto);

  void createChatroomJoin(ChatroomJoinDto chatroomJoinDto);

}
