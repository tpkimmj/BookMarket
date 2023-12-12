package com.book.chat.socket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.chat.service.ChatService;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@Service
@ServerEndpoint("/userChat")
public class UserSocket {
	
	@Autowired
	ChatService chatService;
	
	//searchUser 함수의 filter 표현식을 위한 인터페이스
	private interface SearchExpression {
		//람다식을 위한 함수
		boolean expression(User user);
	}
	//서버와 유저간의 접속을 key로 구분하기 위한 이너 클래스
	private class User {
		Session session;
		String key;
	}
	
	//유저와 서버간의 접속 리스트 -> 동기화처리
	private static List<User> sessionUsers = Collections.synchronizedList(new ArrayList<>());
	
	//리스트에서 탐색(session)
	private static User getUser(Session session) {
		return searchUser(x -> x.session == session);
	}
	
	//리스트에서 탐색(key)
	private static User getUser(String key) {
		return searchUser(x -> x.key.equals(key));
	}
	
	//접속 리스트 탐색
	private static User searchUser(SearchExpression func) {
		Optional<User> op = sessionUsers.stream().filter(x -> func.expression(x)).findFirst();		
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}
		
	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		// 인라인 클래스 User를 생성    
		User user = new User();
		// Unique키를 발급 ('-'는 제거한다.)    
		//user.key = UUID.randomUUID().toString().replace("-", "");
		user.key = UUID.randomUUID().toString().replace("-", "");
		// WebSocket의 세션
		user.session = userSession;
		// 유저 리스트에 등록한다 
		sessionUsers.add(user);
		/* user.session.getBasicRemote().sendText("uuid:" + user.key); */
		// 운영자 Client에 유저가 접속한 것을 알린다
		AdminSocket.visit(user.key);
	}
	
	
	@OnMessage
	public void onMessage(String msg, Session userSession) throws Exception{
		// Session으로 접속 리스트에서 User 클래스를 탐색
		User user = getUser(userSession);
		// 접속 리스트에  User가 있으면
		if (user != null) {
			// 운영자 Client에 유저 Key와 메세지를 보낸다.
			AdminSocket.sendMessage(user.key, msg);
		}
	}
	
	//운영자 -> user 메세지
		public static void sendMessage(String key, String message) {
			User user = getUser(key);
			if (user != null) {
				try {
					//메세지 받음(기존 usersession = 웹소켓세션)
					user.session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	@OnClose
	public void onClose(Session userSession) {
		// Session으로 접속 리스트에서 User 클래스를 탐색
		User user = getUser(userSession);
		// 접속 리스트에  User가 있으면
		if (user != null) {
			// 운영자 Client에 유저 Key로 접속 종료를 알린다
			/* AdminSocket.bye(user.key); */
			sessionUsers.remove(user);
		}
	}
	
	//유저 UK get -> admin에 보낼용도
		public static String[] getUserKeys() {
			String[] ret = new String[sessionUsers.size()];
			for (int i = 0; i < ret.length; i++) {
				ret[i] = sessionUsers.get(i).key;
			}
			return ret;
		}
}