package com.sist.commons;

import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

public class Commons {
	public static void sendDate(HttpServletResponse response, String type, String data) {
		try {
			response.setContentType(type+";charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(data);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
