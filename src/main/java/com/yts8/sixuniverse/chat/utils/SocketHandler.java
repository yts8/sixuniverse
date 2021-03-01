package com.yts8.sixuniverse.chat.utils;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component /*개발자가 직접 작성한 Class를 Bean으로 등록하기 위한 어노테이션*/
/*빈설정 파일이 아니라 빈 클래스에서 빈을 직접 등록할 수 있다.*/
/*value를 이용하여 Bean의 이름을 지정한다.*/
public class SocketHandler extends TextWebSocketHandler {


    /* 웹소켓 세션 저장*/
    Map<String, WebSocketSession> sessionMap = new HashMap<>();

    /* 메세지 발송 */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String chat = message.getPayload();
        for(String member : sessionMap.keySet()){
            WebSocketSession webSession = sessionMap.get(member);
        }

    }

    /* 소켓 연결*/
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(), session);
    }

    /* 소켓 종료*/
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }
}
