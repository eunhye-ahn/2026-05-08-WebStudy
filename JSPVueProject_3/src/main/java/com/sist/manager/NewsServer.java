package com.sist.manager;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import jakarta.websocket.Session;

/**
 * 
 * [목적] 서버가 1분마다 검색어 뉴스 api호출해서 모든 클라이언트에게 실시간으로 뿌리기
 * 		=> 브라우저쪽에서 새로고침없이 자동으로 최신 뉴스가 화면에 갱신되도록
 * 
 * 프로그램 시작
 * 	 | => 접속자 저장 => set:중복방지 => state : 한개만 사용
 * **스케줄러 생성 => 쓰레드
 *   |
 *  1분마다 실행
 *   |
 *  뉴스 검색
 *   |JSON
 *  점속자에게 전송 AJAX 출력
 *    
 */

public class NewsServer {
	//사용자가 페이지 접속 → JS에서 ws://.../news로 WebSocket 연결 → @OnOpen으로 clients에 등록
	//사용자접속공간 생성 => 모든 접속자가 같은 메모리 사용 static
	private static Set<Session> clients = ConcurrentHashMap.newKeySet();
	//중복이 없어야하므로 Set 사용
	//ConcurrentHashMap.newKeySet() : 여러개 쓰레드가 동시접속시 종료해도
	//안전하게 사용할 수 있는 Set 인터페이스 사용
	/**
	 * session : websocket
	 * 세션 아이디 => 고유 번호(접속자 구분) : session.getId()
	 *  연결 상태 확인 => close(),open() => session.isOpen()
	 *  메세지 전송 기능 : session.getBasicRemote().sendText()
	 *  사용자 정보 : 브라우저 , 누구 (IP)
	 *  	=> session.getUserPrincipal()
	 *  						------------ Session에 저장된 경우
	 *  요청 URL => ws://localhost/JSPProject/news 
	 *  접속 시간 
	 *  사용자 속성 => UserProperties (HttpSession)
	 *  
	 */
	
	/**
	 * @Transactional
	 * => Task @
	 */
	private static ScheduledExecutorService scheduler=Executors.newScheduledThreadPool(1);
	
	
	
}
