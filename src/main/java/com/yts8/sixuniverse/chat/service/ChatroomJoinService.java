package com.yts8.sixuniverse.chat.service;


import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.dto.MemberIdDto;

import java.util.List;

public interface ChatroomJoinService {


  // (없으면) 기존의 chat_ref의 최댓값에서 +1해서 리턴
  Long createNewChatRef();

  // 생성한 채팅방번호 각 memberId에 저장하기
  void testCreateNewRoom(ChatroomJoinDto chatroomJoinDto);


  // 공통된 chatRef count
  Long chatRefCount(MemberIdDto memberIdDto);

  // 공통된 chatRef 값 받아오기
  Long getChatRefTest(MemberIdDto memberIdDto);

  List<ChatroomJoinDto> findUserNameByChatRef(Long chatRef);
}
