package com.yts8.sixuniverse.chat.service;


import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.repository.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatroomJoinServiceImpl implements ChatroomJoinService {

  private final ChatMapper chatMapper;


  @Override
  public Long getHostChatRef(Long hostId) {
    return chatMapper.getHostChatRef(hostId);
  }

  @Override
  public Long getMyChatRef(Long memberId) {
    return chatMapper.getMyChatRef(memberId);
  }

  @Override
  public void creatNewRoom(ChatroomJoinDto chatroomJoinDto) { }


  // 존재하는 방이 있는지 체크한다. -> 존재한다면 해당 방 번호를 return 한다.



    // member1과 member2의 id를 전달해서 일치하는 chatRef를 찾는다.
}
