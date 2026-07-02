package com.sist.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sist.vo.NewsVO;

//[WHAT] 네이버 뉴스 API에 검색어 결과값을 요청하는 유틸클래스
//param : 검색어
//result: 검색결과

public class NewsRealManager {
	public static void main(String[] args) {
		newsFindData("축구");
		
	}
	
	public static String newsFindData(String fd){
		String json="";
		
		//api 호출
		String clientId="yEsRh8AkUvfitt10EhaG";
		String clientSecret = "DIcCcMYFqZ";
		
		String text = null;
		try {
			//검색어 url 인코딩
			text = URLEncoder.encode(fd,"UTF-8");
		}catch(Exception e) {
			throw new RuntimeException("검색어 인코딩 실패",e);
		}
		
		//네이버 API URL 조립
		String apiUrl = "https://openapi.naver.com/v1/search/news.json?display=100&query="+text;

		//인증 헤더 생성
		Map<String, String> requestHeader = new HashMap<>();
		requestHeader.put("X-Naver-Client-Id", clientId);
		requestHeader.put("X-Naver-Client-Secret", clientSecret);
		
		String responseBody = get(apiUrl, requestHeader);
		System.out.println(responseBody);
		
		//JSONParser로 JSON → items 배열 순회
		try {
			JSONParser jp=new JSONParser();
			
			JSONObject root=(JSONObject)jp.parse(responseBody);
			System.out.println(root.toJSONString());
			JSONArray arr = (JSONArray)root.get("items");
			json= arr.toJSONString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//VO변환
		
		return json;
	}
	/**
	 * 순수 JAVA(java.net)으로 Http 요청을 보내는 패턴
	 * new URL() 연결객체생성 : 문자열 url을 url 객체로 파싱 => 형식 잘못되면 MalformedURLException 발생
	 * url.openConnection() : 연결준비(URLConnection 객체를 생성하는 단계)
	 * 
	 * 
	 * 
	 */
	
	
	//실제 GET 요청 실행 : get() -> connect()로 HttpURLConnection 생성
	public static String get(String apiUrl, Map<String,String> requestHeader) {
		HttpURLConnection con = connect(apiUrl);
		try {
			//http메서드 지정
			con.setRequestMethod("GET");
			//헤더 설정 반복문
			for(Map.Entry<String, String> header : requestHeader.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			//api 응답 읽기
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
	            return readBody(con.getInputStream());
	        } else {
	            return readBody(con.getErrorStream());
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("API 요청과 응답 실패",e);
		} finally {
			con.disconnect();
		}
		
	}
	
	//URL 연결객체 생성
	public static HttpURLConnection connect(String apiUrl) {
			try {
				URL url = new URL(apiUrl);
				return (HttpURLConnection)url.openConnection();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
			} catch (IOException e) {
				throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
			}
			
	}
	
	//응답 스트림을 문자열로 반환
	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);
	    try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	        StringBuilder responseBody = new StringBuilder();
	        String line;
	        while ((line = lineReader.readLine()) != null) {
	            responseBody.append(line);
	        }
	        return responseBody.toString();
	    } catch (IOException e) {
	        throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
	    }
	}
}
