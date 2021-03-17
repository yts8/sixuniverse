package com.yts8.sixuniverse.chat.service;


import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;

import java.util.List;

public interface ChatroomJoinService {
  void creatNewRoom(ChatroomJoinDto chatroomJoinDto);


  // 공통된 chatRef 있는지 확인
  Long getChatRef(Long myMemberId, Long hostId);

  // (없으면) 기존의 chat_ref의 최댓값에서 +1해서 리턴
  Long createNewChatRef();

  // 채팅방 생성
  void testCreateNewRoom(ChatroomJoinDto chatroomJoinDto);


  List getHostChatRef(Long hostId);
  List getMyChatRef(Long myMemberId);
}
