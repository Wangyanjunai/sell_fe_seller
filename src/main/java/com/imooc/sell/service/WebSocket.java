package com.imooc.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Describe:
 *
 * @Author 王艳军
 * @Date 2017/12/19 11:36:56
 */
@Component
@Slf4j
@ServerEndpoint(value = "/websocket")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

    @OnOpen
    public void onOpen(Session session) throws Exception{
        this.session = session;
        webSocketSet.add(this);
        log.info("【WebSocket消息】 有新的连接，连接总数：{}", webSocketSet.size());
    }

    @OnClose
    public void onClose(Session session) {
        webSocketSet.remove(this);
        log.info("【WebSocket消息】 连接断开，连接总数：{}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【WebSocket消息】 收到客户端发来的消息：{}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket:webSocketSet) {
            log.info("【WebSocket消息】 广播消息：message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("【WebSocket消息】 广播消息出现IOException,e={}", e.getLocalizedMessage());
            }
        }
    }
}
