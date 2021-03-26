package com.yts8.sixuniverse.chat.utils;


import com.google.gson.Gson;
import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ReceiveMessageDto;
import com.yts8.sixuniverse.chat.dto.SendMessageDto;
import com.yts8.sixuniverse.chat.service.ChatService;
import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

@Component
@RequiredArgsConstructor // final 붙은 애들만 생성자가 만들어진다.
@MessageMapping
public class SocketHandler extends TextWebSocketHandler {

  private HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
  private final ChatService chatService;
  private final MemberService memberService;


  // 메세지 보내기
  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws ParseException {


    String msg = message.getPayload(); //메세지에 담긴 텍스트값을 가져온다.

    Gson gson = new Gson();
    ReceiveMessageDto receiveMessageDto = gson.fromJson(msg, ReceiveMessageDto.class);
    System.out.println(receiveMessageDto);

    Long chatRef = receiveMessageDto.getChatRef();
    String content = receiveMessageDto.getMsg();
    Long memberId = receiveMessageDto.getMemberId();
    Long joinNum = chatService.findByChatRef(chatRef);

    ChatDto chatDto = new ChatDto();
    chatDto.setJoinNum(joinNum);
    chatDto.setMemberId(memberId);
    chatDto.setContent(content);


    chatService.saveMessage(chatDto); // DB


    SendMessageDto sendMessageDto = gson.fromJson(msg, SendMessageDto.class);

    MemberDto member = memberService.findById(memberId);
    sendMessageDto.setMemberId(member.getMemberId());
    sendMessageDto.setUsername(member.getUsername());
    sendMessageDto.setProfileImg(member.getProfileImg());
    sendMessageDto.setDate(LocalDateTime.now());














    /* 디비작업 수행 */
    //메시지 발송

    JSONObject obj = jsonToObjectParser(msg);
    for (String key : sessionMap.keySet()) {
      WebSocketSession wss = sessionMap.get(key);
      try {
        wss.sendMessage(new TextMessage(gson.toJson(sendMessageDto))); // JSON 문자열로 만든다.


      } catch (Exception e) {
        e.printStackTrace();
      }
    }
//
//    JSONParser p = new JSONParser();
//    JSONObject jboj = (JSONObject) p.parse(String.valueOf(obj));

//    String content = jboj.get("msg").toString();
//    Long memberId = Long.parseLong(jboj.get("memberId").toString());
//    Long joinNum = Long.parseLong(jboj.get("joinNum").toString());
//





    /* 채팅 메세지 저장 */

//    ChatDto chatDto = new ChatDto();
//
//    chatDto.setContent(content);
//    chatDto.setMemberId(memberId);
//    chatDto.setJoinNum(joinNum);
//
//    chatService.saveMessage(chatDto);

  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    //소켓 연결
    super.afterConnectionEstablished(session);
    sessionMap.put(session.getId(), session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    //소켓 종료
    sessionMap.remove(session.getId());
    super.afterConnectionClosed(session, status);
  }


  private static JSONObject jsonToObjectParser(String jsonStr) {
    JSONParser parser = new JSONParser();
    JSONObject obj = null;
    try {
      obj = (JSONObject) parser.parse(jsonStr);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return obj;
  }


}


