package com.yts8.sixuniverse.chat.service;


import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.repository.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ChatMapper chatMapper;

  @Override
  public void createChatroomJoin(ChatroomJoinDto chatroomJoinDto) {
    chatMapper.createChatroomJoin(chatroomJoinDto);
  }

  @Override
  public Long findByChatRef(Long chatRef) {
    return chatMapper.findByChatRef(chatRef);
  }

  @Override
  public Long findMemberIdByChatRef(Long chatRef) {
    return chatMapper.findMemberIdByChatRef(chatRef);
  }


  @Override
  public void saveMessage(ChatDto chatDto) {
    chatMapper.saveMessage(chatDto);
  }



}

