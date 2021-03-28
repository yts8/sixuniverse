package com.yts8.sixuniverse.chat.service;

import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatListDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.dto.MessageDto;

import java.util.List;


public interface ChatService {

  /* 채팅메세지 보내기*/
  void saveMessage(ChatDto chatDto);

  Long findByChatRef(Long chatRef);

  List<ChatDto> findMessageByChatRef(Long chatRef);

  Long findMessages(Long chatRef);

  List<MessageDto> getLastChat(Long myMemberId);

  Long findHostId(ChatListDto chatListDto);

  String findUsernameById(Long hostId);

  Long countReplyOfHost(Long hostId);

  Long countHostRoom(Long hostId);


  String findOtherName(ChatListDto chatListDto);
  String findOtherProfile(ChatListDto chatListDto);
}
