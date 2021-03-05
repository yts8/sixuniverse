package com.yts8.sixuniverse.chat.service;


import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.repository.ChatMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

  private final ChatMapper chatMapper;

  @Override
  public void insertChat(ChatDto chatDto) {
    chatMapper.insertChat(chatDto);
  }

  @Override
  public void createChatroomJoin(ChatroomJoinDto chatroomJoinDto) {
    chatMapper.createChatroomJoin(chatroomJoinDto);
  }
}
