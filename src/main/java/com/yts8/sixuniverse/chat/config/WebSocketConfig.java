
package com.yts8.sixuniverse.chat.config;

import com.yts8.sixuniverse.chat.utils.SocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {


  private final SocketHandler socketHandler;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
   registry.addHandler(socketHandler, "/chating/{chatroomId}");
 //  registry.addHandler(socketHandler, "/chating/{memberId}");
  }
}