package com.imooc.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

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

    //	与某个客户端的连接会话，需要通过它来给客户端发送数据。
    private Session session;

    //	concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

    /**
     * @Title: onOpen
     * @Description 连接建立成功调用的方法
     * @param session
     * @return void 返回值类型
     * @throws Exception
     */
    @OnOpen
    public void onOpen(Session session) throws Exception {
        try {
            this.session = session;
            webSocketSet.add(this);
            log.info("【WebSocket消息】 有新的连接，连接总数：{}。", webSocketSet.size());
        } catch (Exception e) {
            log.error("【WebSocket消息】有新的连接，将其添加到WebSocket出现错误。", e);
        }
    }

    /**
     * @Title onClose
     * @Description 连接关闭调用方法
     * @param session 传入参数
     * @return void 返回值类型
     */
    @OnClose
    public void onClose(Session session) {
        try {
            this.session = session;
            webSocketSet.remove(this);
            this.session.close();
            log.info("【WebSocket消息】 连接断开，连接总数：{}", webSocketSet.size());
        } catch (Exception e) {
            log.error("【WebSocket消息】连接断开，将其从WebSocket删除出现错误。", e);
        }
    }

    /**
     * @Title onMessage
     * @Description 收到客户端消息后调用的方法
     * @param message 客户端消息
     * @return void 返回值类型
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("【WebSocket消息】 收到客户端发来的消息：{}。", message);
    }

    /**
     * @Title onError
     * @Description WebSocket发生错误时调用
     * @param session
     * @param error
     * @return void 返回值类型
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("【WebSocket消息】WebSocket出现错误。", error);
        try {
            this.session = session;
            this.session.close();
        } catch (Exception e) {
            log.error("【WebSocket消息】WebSocket关闭session出现错误。", error);
        }
    }

    /**
     * @Title: sendMessage
     * @Description 群发 WebSocket自定义消息
     * @param message 传入参数
     * @return void 返回值类型
     */
    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("【WebSocket消息】 广播消息：message={}。", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                log.error("【WebSocket消息】 广播消息出现错误。", e.getLocalizedMessage());
            }
        }
    }
}
