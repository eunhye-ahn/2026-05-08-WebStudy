package com.sist.model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.dao.ReplyDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.ReplyVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {
	@RequestMapping("food/detail_before.do")
	public String food_detail_before(HttpServletRequest request, HttpServletResponse response) {
		
		//방문한 내용 쿠키에 저장
		String no = request.getParameter("no");
		Cookie cookie = new Cookie("food_"+no, no);
		cookie.setMaxAge(60*60*24);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return "redirect:../food/detail.do?no="+no;	//redirect : 리퀘스트 초기화
		//sendRedirect()
	}
	/*
	 * 쿠키는 브라우저에 저장 (보안 취약)	=> 문자열만 저장 가능 (자동로그인, 장바구니 저장, 최근방문)
	 * 세션은 서버에 저장 (보안이 뛰어나다)	=> Object 단위로 저장 가능 (접속자 일부 정보를 저장 시에)
	 * -----------------------------------------------------------------------
	 * request를 이용해서 생성이 가능
	 * Cookie[] cookies = request.getCookies()
	 * HttpSession session = request.getSession()
	 */
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		//값받기
		String no = request.getParameter("no");
		//include
		request.setAttribute("main_jsp", "../food/detail.jsp");
		//dao연결
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(no));
		//결과값보내기
		request.setAttribute("vo", vo);
		
		List<ReplyVO> list = ReplyDAO.replyListData(Integer.parseInt(no));
		request.setAttribute("rList", list);
		request.setAttribute("rCount", list.size());
		
		
		return "../main/main.jsp";	//forward : 리퀘스트를 유지
		//forward()
	}
	//화면변경
	@RequestMapping("food/find.do")
	public String food_find(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../food/find.jsp");
		return "../main/main.jsp";
	}
	/**
	 * ??????????????????
	 * Vue(thymeleaf) / jquery(jsp) ==> CDN
	 * => 필요한 부분에서만 사용 / 보안 (소스노춡X)
	 * 
	 * react (단독) === spring boot + nodeJS
	 */
	//검색어를 유지해야하기 때문에 ajax로 처리한다
	@RequestMapping("food/find_ajax.do")
	public void food_find_ajax(HttpServletRequest request, HttpServletResponse response) {
		//값받아서 hashmap 채우기
		String fd = request.getParameter("fd");	//<input type="text" name="fd"> ??name? value?
		if(fd == null) {
			fd="마포";
		}
		String col = request.getParameter("col");	//<select> name/address/type </select>
		if(col==null) {
			col="address";
		}
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		
		Map map = new HashMap();
		map.put("start", (curpage*12)-12);
		map.put("fd", fd);
		map.put("column", col);
		
		List<FoodVO> list = FoodDAO.foodFindData(map);
		int totalpage = FoodDAO.foodFindTotalPage(map);
		
		final int BLOCK=10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		// no,name,poster,address
		//jsp찾아서 json으로 데이터 전송
		try {
			//list가 => jsonArray
			//vo => jsonObject
			//로 변경
			JSONArray arr = new JSONArray();
			int j =0;
			for(FoodVO vo : list) {
				JSONObject obj = new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("name", vo.getName());
				obj.put("poster", vo.getPoster());
				obj.put("address", vo.getAddress());
				if(j==0) {
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
					obj.put("startPage", startPage);
					obj.put("endPage", endPage);
				}
				arr.add(obj);
				j++;
			}
			
			//arr데이터를 Ajax(js)로 전송 => Restful ??? : 데이터연결수행
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(arr.toJSONString());
		}catch(Exception e) {}
		
		
	}
	@RequestMapping("food/movie.do")
	public String movie_find(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../food/movie.jsp");
		return "../main/main.jsp";
	}
	
}
