package com.yts8.sixuniverse.chat.repository;


import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
  /*채팅메세지 저장*/
  void saveMessage(ChatDto chatDto);
  void createChatroomJoin(ChatroomJoinDto chatroomJoinDto);



  Long getChatRef(Long myMemberId, Long hostId);
  Long createNewChatRef();

  void testCreateNewRoom(ChatroomJoinDto chatroomJoinDto);

  List getHostChatRef(Long hostId);

  List getMyChatRef(Long myMemberId);





  Long getRealChatRef(Long myMemberId, Long hostId);
  Long getRealRealChatRef(Long myMemberId, Long hostId);

  Long chatRefCount(Long myMemberId,Long hostId);
  Long getChatRefTest(Long myMemberId, Long hostId);

  Long findByChatRef(Long chatRef);

  Long findMemberIdByChatRef(Long chatRef);
}
