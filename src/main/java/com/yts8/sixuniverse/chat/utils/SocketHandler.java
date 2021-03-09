package com.yts8.sixuniverse.chat.utils;


import com.yts8.sixuniverse.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Component
@RequiredArgsConstructor // final 붙은 애들만 생성자가 만들어진다.
public class SocketHandler extends TextWebSocketHandler {

  HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
  private final ChatService chatService;
  private final HttpSession httpSession;

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) {


    System.out.println("222222222222222222222222");
    System.out.println(message);
    System.out.println(message.getPayload());

    String msg = message.getPayload(); //메세지에 담긴 텍스트값을 가져온다.

    //String msg를 변환해야 한다.


    /* 디비작업 수행 */

    //메시지 발송

    for (String key : sessionMap.keySet()) {
      WebSocketSession wss = sessionMap.get(key);
      try {
        wss.sendMessage(new TextMessage(msg));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
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


}


