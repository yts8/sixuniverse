package com.yts8.sixuniverse.chat.repository;


import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {
  /*채팅메세지 저장*/
  void insertChat(ChatDto chatDto);
  void createChatroomJoin(ChatroomJoinDto chatroomJoinDto);

  /*(채팅방리스트용) 채팅정보 불러오기*/


}
