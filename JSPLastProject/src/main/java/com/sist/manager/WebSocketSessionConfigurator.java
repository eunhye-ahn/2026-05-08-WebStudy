package com.sist.manager;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.ServerEndpointConfig;
import jakarta.websocket.server.ServerEndpointConfig.Configurator;

//websocket 안에서 httpsession을 사용할 수 있게 설정

/**
 * 웹소켓에서 httpsession을 사용할 수 잇게 설정
 * 	1.사용자로그인 => id,name 저장 => httpSession
 * 					|
 * 				websocket에 접속 (ws://.../chat)
 * 					| 웹소켓 프로토콜로 변경(http->ws) : handshake
 * 	2.modifyHandshake() 실행 => httpsession 저장
 * 					|
 * 				request.getHttpSession() 가져오기
 * 					|
 * 				ServerEndpointConfig의 UserProperties에 저장
 * 	3.OnOpen() 연결시 실행
 * 					| HttpSession을 가지고 온다
 * 	4.OnMessage() 실행 => 채팅 시작
 * 
 * 	HttpSession ==== WebSocket에 연결하는 역할 
 * 				
 */

public class WebSocketSessionConfigurator extends Configurator{
	
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		//http요청 => httpsession읽기
		HttpSession session = (HttpSession)request.getHttpSession();
		//세션이 존재하는 경우
		if(session != null) {
			//websocket에서 사용이 가능하도록 저장 => UserProperties
			//key / value => Map형식으로
			sec.getUserProperties().put(HttpSession.class.getName(),session);
		}
	}
}
