package com.logmei.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;


@ServerEndpoint("/websocket")
@Component
@Slf4j
public class WebSocetServer {
    static {
        System.out.println("websocetserver init-----------------------------------------------");
    }

    //在线人数，多线程 需要用原子变量
    public static AtomicInteger onLineCount = new AtomicInteger(0);
    public static CopyOnWriteArraySet<WebSocetServer> webSocetSet = new CopyOnWriteArraySet<WebSocetServer>();
    //连接会话
    private Session session;

    //连接建立成功
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocetSet.add(this);//加入set
        log.info("websocet 建立连接成功,当前人数为{}",onLineCount.incrementAndGet());

    }

    //关闭连接
    @OnClose
    public void onClose(){
        webSocetSet.remove(this);
        log.info("websocet 断开连接,当前人数为{}",onLineCount.decrementAndGet());

    }

    //来自客户端的消息
    @OnMessage
    public void getMessage(String message,Session session){
        log.info("【websocet】来自客户端消息"+message);
        sendMessage("您有一个新订单"+message);

    }

    //发送消息

    public void sendMessage(String message){
        try {
            for (WebSocetServer socetServer : webSocetSet){
                log.info("【websocet】"+message);
                socetServer.session.getBasicRemote().sendText(message);
            }

        } catch (IOException e) {
            log.error("【websocet】发送消息失败");
        }
    }

    //发生错误
    @OnError
    public void onError(Session session, Throwable error){
        try {
            session.close();
        } catch (IOException e) {
           log.error("【websocket】 关闭时出错");
        }
        log.error("【websocket】发生错误");
        error.printStackTrace();
    }


}
