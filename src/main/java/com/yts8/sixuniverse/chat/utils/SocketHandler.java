package com.yts8.sixuniverse.chat.utils;


import com.yts8.sixuniverse.chat.dto.ChatDto;
import com.yts8.sixuniverse.chat.dto.ChatroomJoinDto;
import com.yts8.sixuniverse.chat.service.ChatService;
import com.yts8.sixuniverse.chat.service.ChatroomJoinService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Component
@RequiredArgsConstructor // final 붙은 애들만 생성자가 만들어진다.
@MessageMapping
public class SocketHandler extends TextWebSocketHandler {

  HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
  private final ChatService chatService;
  private final ChatroomJoinService chatroomJoinService;
  private final HttpSession httpSession;
  private final HttpServletRequest request;



  // 메세지 보내기
  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws ParseException {


    System.out.println("여기가 첫번째");
    System.out.println("message : " + message);
    System.out.println("message.getPayload() : " + message.getPayload());

   String msg =  message.getPayload(); //메세지에 담긴 텍스트값을 가져온다.
    // String msg를 변환해야 한다.
    /* 디비작업 수행 */


    //메시지 발송
    JSONObject obj = jsonToObjectParser(msg);
    for(String key : sessionMap.keySet()) {
      WebSocketSession wss = sessionMap.get(key);
      try {
        wss.sendMessage(new TextMessage(obj.toJSONString())); // JSON 문자열로 만든다.
        System.out.println("여기가 두번째 : " + obj.toJSONString());
      }catch(Exception e) {
        e.printStackTrace();
      }
    }

    JSONParser p = new JSONParser();
    JSONObject jboj = (JSONObject) p.parse(String.valueOf(obj));

    String content = jboj.get("msg").toString();
    Long memberId = Long.parseLong(jboj.get("memberId").toString());
  //  Long joinNum = Long.parseLong(jboj.get("memberId").toString());
    String userName = jboj.get("userName").toString();
    Long realChatRef = Long.parseLong(jboj.get("realChatRef").toString());


    /* 저장공간 생성 */
    ChatroomJoinDto chatroomJoinDto = new ChatroomJoinDto();
    ChatDto chatDto = new ChatDto();

    chatDto.setContent(content);
    chatDto.setMemberId(memberId);
    // chatDto.setJoinNum(joinNum);

    chatroomJoinDto.setName(userName);
    chatroomJoinDto.setMemberId(memberId);
    chatroomJoinDto.setChatRef(realChatRef);



    chatroomJoinService.creatNewRoom(chatroomJoinDto);
    chatService.saveMessage(chatDto);

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


