package com.yts8.sixuniverse.chat.service;


import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;

public interface ChatroomJoinService {


  // (없으면) 기존의 chat_ref의 최댓값에서 +1해서 리턴
  Long createNewChatRef();

  // 생성한 채팅방번호 각 memberId에 저장하기
  void testCreateNewRoom(ChatroomJoinDto chatroomJoinDto);


  // 공통된 chatRef count
  Long chatRefCount(Long myMemberId, Long hostId);

  // 공통된 chatRef 값 받아오기
  Long getChatRefTest(Long myMemberId, Long hostId);
}
