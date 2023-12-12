package com.book.chat.socket;
import java.io.IOException;

import org.springframework.stereotype.Service;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@Service
@ServerEndpoint("/adminChat")
public class AdminSocket {
	// 운영자 유저는 하나라고 가정하고 만약 둘 이사의 세션에서 접속을 하면 마지막 세션만 작동한다
	private static Session admin = null;
	
	// 운영자 유저가 접속을 하면 발생하는 이벤트 함수
	@OnOpen
	public void onOpen(Session adminSession) {
		// 기존에 운영자 유저의 소켓이 접속중이라면
		if (admin != null) {
			try {
				// 접속을 끊는다 
				admin.close();
			} catch (IOException e) {
				
			}
		}
		// 운영자 유저의세션을 바꾼다
		admin = adminSession;
		// 기존에 접속해 있는유저의 정보를 운영자 client로 보낸다.
		for(String key : UserSocket.getUserKeys()) {
			// 전송
			visit(key);
		}
	}
	
	
	@OnMessage
	public void onMessage(String msg, Session adminSession) throws IOException{
		// key와 메세지 구분키를 #####을 넣었다
		String[] split = msg.split("#####", 2);
		// 앞은 key 데이터
		String Key = split[0];
		// 뒤 정보는 메세지
		String message = split[1];
		// 일반 유저의  key로 탐색후 메세지 전송
		UserSocket.sendMessage(Key, message);
	}
	
	@OnClose
	public void onClose(Session adminSession) {
		admin = null;
	}
	
	// 운영자 유저로 메세지를 보내는 함수
	private static void send(String msg) {
		if (admin != null) {
			try {
				admin.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//아래의 조건들로 방생성, 방종료, 메세지전송
	//유저 입장
	public static void visit(String key) {		
		send("{\"status\":\"visit\", \"key\":\"" + key + "\"}");
	}
	
	//유저 message 받음
	public static void sendMessage(String key, String message) throws IOException {
		send("{\"status\":\"message\", \"key\":\"" + key + "\", \"message\":\"" + message + "\"}");
	}
	
	//유저 나감
	public static void bye(String key) {
		send("{\"status\":\"bye\", \"key\":\"" + key + "\"}");
	}
}