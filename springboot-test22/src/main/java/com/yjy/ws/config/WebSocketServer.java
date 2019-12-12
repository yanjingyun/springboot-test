package com.yjy.ws.config;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer {

	private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

	private static AtomicInteger onlineCount = new AtomicInteger(0); // 在线数
	private Session session; // 连接会话，用来给客户端发送数据
	private String sid; // 接收sid

	// 存放每个客户端对应的MyWebSocket对象 <sid, this>
	private static Map<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("sid") String sid) {
		this.sid = sid;
		this.session = session;

		System.out.println(this);
		webSocketMap.put(sid, this); // 将WebSocketServer对象加入set中

		addOnlineCount(); // 在线数加1
		log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());

		sendMessage(sid, "连接成功");
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketMap.remove(sid);
		subOnlineCount(); // 在线数减1
		log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误");
		error.printStackTrace();
	}

	/**
	 * 收到客户端消息后调用的方法
	 */
	@OnMessage
	public void onMessage(String message) {
		log.info("收到来自窗口" + sid + "的信息:" + message);
		sendMessage(sid, "已回应消息");
	}

	/**
	 * 群发推送
	 */
	public static void sendMessage(String message) {
		for (String sid : webSocketMap.keySet()) {
			webSocketMap.get(sid).sendText(message);
		}
	}

	/**
	 * 推送给某人推送
	 */
	public static void sendMessage(String sid, String message) {
		webSocketMap.get(sid).sendText(message);
	}

	private void sendText(String message) {
		try {
			session.getBasicRemote().sendText("服务端回应：" + message + "<br>");
		} catch (IOException e) {
			log.error("websocket IO异常");
			e.printStackTrace();
		}
	}

	public AtomicInteger getOnlineCount() {
		return onlineCount;
	}

	public int addOnlineCount() {
		return onlineCount.getAndIncrement();
	}

	public int subOnlineCount() {
		return onlineCount.getAndDecrement();
	}
}
