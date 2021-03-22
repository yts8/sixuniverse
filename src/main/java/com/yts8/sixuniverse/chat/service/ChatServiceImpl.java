package com.yts8.sixuniverse.chat.service;


import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.dto.MemberIdDto;
import com.yts8.sixuniverse.chat.dto.MessageDto;
import com.yts8.sixuniverse.chat.repository.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ChatMapper chatMapper;

  @Override
  public Long findByChatRef(Long chatRef) {
    return chatMapper.findByChatRef(chatRef);
  }

  @Override
  public List<ChatDto> findMessageByChatRef(Long chatRef) {
    return chatMapper.findMessageByChatRef(chatRef);
  }

  @Override
  public Long findMessages(Long chatRef) {
    return chatMapper.findMessages(chatRef);
  }

  @Override
  public List<MessageDto> getLastChat(Long myMemberId) {return chatMapper.getLastChat(myMemberId);}

  @Override
  public Long countLastChat(Long chatRef) {return chatMapper.countLastChat(chatRef);}

  @Override
  public void saveMessage(ChatDto chatDto) {
    chatMapper.saveMessage(chatDto);
  }

}

