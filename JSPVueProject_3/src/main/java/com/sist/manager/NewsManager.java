package com.sist.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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

/**
 * [WHAT] 네이버 뉴스 검색 오픈API 호출 후 뉴스 데이터를 가져오는 유틸리티 클래스
 * 
 * newsFindData(검색어)
 * 검색어 url 인코딩
 * 네이버 API URL 조립 + 인증 헤더 생성
 * get() -> connect()로 HttpURLConnection 생성 → GET 요청
 * 응답(JSON 문자열) → readBody()로 파싱
 * JSONParser로 JSON → items 배열 순회
 * title/desc/link 추출 → NewsVO에 담아서 List에 저장
 * List<NewsVO> 반환
 * 
 */
public class NewsManager {
	public static void main(String[] args) {
		newsFindData("축구");
		
	}
	
	public static List<NewsVO> newsFindData(String fd){
		List<NewsVO> list = new ArrayList<NewsVO>();
		
		//api 호출
		String clientId="yEsRh8AkUvfitt10EhaG";
		String clientSecret = "DIcCcMYFqZ";
		
		String text = null;
		try {
			//검색어 url 인코딩
			text = URLEncoder.encode(fd,"UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
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
			for(int i=0;i<=arr.size();i++) {
				JSONObject obj = (JSONObject)arr.get(i);
				
				String title = (String)obj.get("title");
	        	String desc=(String)obj.get("description");
	        	String link=(String)obj.get("link");
	        	
	        	
	        	NewsVO vo=new NewsVO();
	            vo.setTitle(title);
	            vo.setDesc(desc);
	            vo.setLink(link);
	              
	            list.add(vo);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//VO변환
		
		return list;
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
