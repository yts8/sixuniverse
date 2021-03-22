package com.yts8.sixuniverse.chat.service;


import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.dto.MemberIdDto;
import com.yts8.sixuniverse.chat.repository.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomJoinServiceImpl implements ChatroomJoinService {

  private final ChatMapper chatMapper;

  /*(없으면) 기존의 chat_ref의 최댓값에서 +1해서 리턴*/
  @Override
  public Long createNewChatRef() {
    return chatMapper.createNewChatRef();
  }

  /* 생성한 채팅방번호 각 memberId에 저장하기 */
  @Override
  public void testCreateNewRoom(ChatroomJoinDto chatroomJoinDto) {
    chatMapper.testCreateNewRoom(chatroomJoinDto);
  }

  /* 공통된 chatRef count */
  @Override
  public Long chatRefCount(MemberIdDto memberIdDto) {return chatMapper.chatRefCount(memberIdDto);}

  /* 공통된 chatRef 값 받아오기 */
  @Override
  public Long getChatRefTest(MemberIdDto memberIdDto) {
    return chatMapper.getChatRefTest(memberIdDto);
  }

  @Override
  public List<ChatroomJoinDto> findUserNameByChatRef(Long chatRef) {
    return chatMapper.findUserNameByChatRef(chatRef);
  }


}
