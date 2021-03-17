package com.yts8.sixuniverse.chat.service;


import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.repository.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomJoinServiceImpl implements ChatroomJoinService {

  private final ChatMapper chatMapper;


  @Override
  public void creatNewRoom(ChatroomJoinDto chatroomJoinDto) { }


  /* 공통된 chatRef 있는지 확인 */
  @Override
  public Long getChatRef(Long myMemberId, Long hostId) {
    return chatMapper.getChatRef(myMemberId,hostId);
  }

  /*(없으면) 기존의 chat_ref의 최댓값에서 +1해서 리턴*/
  @Override
  public Long createNewChatRef() {
    return chatMapper.createNewChatRef();
  }

  /* ChatController에서 채팅방 먼저 생성 */
  @Override
  public void testCreateNewRoom(ChatroomJoinDto chatroomJoinDto) {
    chatMapper.testCreateNewRoom(chatroomJoinDto);
  }

  @Override
  public List getHostChatRef(Long hostId) {
    return chatMapper.getHostChatRef(hostId);
  }
  @Override
  public List getMyChatRef(Long myMemberId) {
    return chatMapper.getMyChatRef(myMemberId);
  }

}
