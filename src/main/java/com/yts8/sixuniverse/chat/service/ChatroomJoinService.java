package com.yts8.sixuniverse.chat.service;


import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;

public interface ChatroomJoinService {


  /* 호스트 아이디 넘겨서 chatRef 받아오기*/
  Long getHostChatRef(Long hostId);
  Long getMyChatRef(Long memberId);
  void creatNewRoom(ChatroomJoinDto chatroomJoinDto);



}
