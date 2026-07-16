package com.sist.vo;

import jakarta.websocket.Session;
import lombok.Data;

@Data
public class ChatVO {
	private String id,name;
	private Session session;			//사용자정보(ip,port):구분자가된다(누가보냈는지확인을위한) <-websocket session (httpsession X)
}
