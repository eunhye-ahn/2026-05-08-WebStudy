package com.sist.manager;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/**
 * 브라우저에서 접속
 * 		|
 * WebSocket 연결 (@OnOpen)
 * 		|
 * 	id,name을 가져온다 (httpsession)
 * 		|
 * 	map에 저장
 * 		|
 * 	1)입장 메세지 전송
 * 		|	
 * 	2)채팅 시작
 * 		|
 * 	@OnMessage 실행
 * 		|
 * 	3)전체사용자반복
 * 		|
 * 	--------------
 * |			  |
 * me			you
 * 		|
 * 	4)종료 (@OnClose) => Map에서 삭제
 * 		|
 * 	퇴장 메세지 전송
 */
import java.util.*;

import com.sist.vo.ChatVO;

@ServerEndpoint(value="/chat",configurator = WebSocketSessionConfigurator.class)
public class ChatManager {
	//접속자 저장공간(동기화로 저장)
	private static Map<Session,ChatVO> users=Collections.synchronizedMap(new HashMap<Session,ChatVO>());
	
	//접속시 처리 (입장시 실행)
	@OnOpen
	public void connection(Session session,EndpointConfig config) throws Exception{
		//사용자정보 저장
		ChatVO vo = new ChatVO();
		HttpSession hs = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
		vo.setId((String)hs.getAttribute("id"));
		vo.setName((String)hs.getAttribute("name"));
		vo.setSession(session);
		
		users.put(session, vo);
		
		//현재 접속한 사람에게 입장 메시지 전송
		/**
		 * Map,Set => 인덱스번호 X => 순차적으로 출력 X
		 * => Iterator => 순차적으로 한개씩 접근이 가능
		 * => hasNext() : 존재여부 확인 (true)
		 * => next() : 다음데이터로 이동
		 * => remove() : 삭제
		 * 
		 */
		Iterator<Session> it = users.keySet().iterator();
		while(it.hasNext()) {
			Session ss = it.next();
			//본인이 아닌 사람들에게 입장 메시지 전송
			if(!ss.getId().equals(session.getId())) {
				//sendText : 브라우저로 전송
				ss.getBasicRemote().sendText("msg:[🤗 알림]"+vo.getName()+"님이 입장하셨습니다");
			}
		}
		System.out.println("클라이언트 접속:"+vo.getId()+", "+vo.getName()+", "+vo.getSession().getId());
	}
	
	//접속 종료 (퇴장시 실행)
	@OnClose
	public void disConnection(Session session) throws Exception{
		Iterator<Session> it = users.keySet().iterator();
		while(it.hasNext()) {
			Session ss = it.next();
			//퇴장한 유저 정보가져오기
			ChatVO vo = users.get(session);
			//본인이 아닌 사람들에게 입장 메시지 전송
			if(!ss.getId().equals(session.getId())) {
				//sendText : 브라우저로 전송
				ss.getBasicRemote().sendText("msg:[🤗 알림]"+vo.getName()+"님이 퇴장하셨습니다");
			}
		}
		System.out.println("클라이언트 퇴장:"+users.get(session).getId());
		
		//목록에서 제거
		users.remove(session);
	}
	
	//채팅 시작
	@OnMessage
	public void message(String message,Session session) throws Exception{
		System.out.println("수신된 메시지:"+message+", "+users.get(session).getName());
		//접속한 모든사람에게 전송
		Iterator<Session> it = users.keySet().iterator();
		while(it.hasNext()) {
			//접속자 한사람씩 읽기
			Session ss = it.next();
			//메시지 보낸사람
			ChatVO vo = users.get(session);
			//보낸사람 구분에서 브라우저에 전송
			if(session.getId().equals(ss.getId())) {
				ss.getBasicRemote().sendText("me:["+vo.getName()+"]"+message);
			}else {
				ss.getBasicRemote().sendText("you:["+vo.getName()+"]"+message);
			}
		}
	}
	
	//오류처리
	@OnError
	public void error(Session session,Throwable ex) {
		ex.printStackTrace();
	}
}
