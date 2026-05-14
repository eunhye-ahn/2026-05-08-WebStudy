package com.sist.temp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class MovieManager {
	private String[] urls = {
			"",
			"searchMainDailyBoxOffice.do",
			"searchMainRealTicket.do",
			"searchMainDailySeatTicket.d"
	};
	private String baseUrl = "https://www.kobis.or.kr/kobis/business/main/";
	public String movieListData(int no) {
		String data = "";
		try {
			//사이트연결에서 데이터 가져오기
			URI uri = new URI(baseUrl + urls[no]);
			URL url = uri.toURL();
			HttpURLConnection conn =(HttpURLConnection)url.openConnection();
			if(conn != null) 	//사이트 연결완료
			{
				//url에 있는 데이터 가져오기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while(true)
				{
					String s = br.readLine();
					if(s==null) {
						break;
					}
					data+=s;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static void main(String[] args) {
		MovieManager m = new MovieManager();
		String s = m.movieListData(1);
		System.out.println(s);
	}
}
