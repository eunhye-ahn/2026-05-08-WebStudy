package com.sist.manager;

import jakarta.websocket.server.ServerEndpointConfig.Configurator;

//websocket 연결 시 httpsession을 웹소켓으로 전달하기 위한 클래스
public class WebSocketSessionConfigurator{
	//websocket 연결 직전에 자동으로 호출되는 메서드
	/**
	 * websokcet은 접속자별로 개인화된 데이터를 연결해야한다
	 * 예를들면 알림기능, 채팅 처럼 
	 * 
	 * handshake 
	 * 		HTTP ==> WS변경
	 */
}
