package com.yts8.sixuniverse.chat.repository;


import com.yts8.sixuniverse.chat.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
  /*채팅메세지 저장*/
  void saveMessage(ChatDto chatDto);

  Long createNewChatRef();

  void testCreateNewRoom(ChatroomJoinDto chatroomJoinDto);

  Long findByChatRef(Long chatRef);


  /* 채팅 출력용 */

  List<ChatroomJoinDto> findUserNameByChatRef(Long chatRef);

  List<ChatDto> findMessageByChatRef(Long chatRef);

  Long findMessages(Long chatRef);

  List<MessageDto> getLastChat(Long myMemberId);

  Long countLastChat(Long chatRef);

  Long chatRefCount(MemberIdDto memberIdDto);

  Long getChatRefTest(MemberIdDto memberIdDto);

  Long findHostId(ChatListDto chatListDto);

  String findUsernameById(Long hostId);

  Long countReplyOfHost(Long hostId);

  Long countHostRoom(Long hostId);
}
