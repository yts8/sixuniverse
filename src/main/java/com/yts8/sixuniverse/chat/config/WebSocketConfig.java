package com.yts8.sixuniverse.chat.config;

import com.yts8.sixuniverse.chat.utils.SocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

public class WebSocketConfig implements WebSocketConfigurer { /*webSocketHandler를 사용할 수 있도록 registry에 등록*/


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketHandler(), "/chat");
    }
}
