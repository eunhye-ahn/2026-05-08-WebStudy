package com.sist.model;

import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.JjimDAO;
import com.sist.dao.ReserveDAO;
import com.sist.vo.JjimVO;
import com.sist.vo.ReserveVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MypageModel {
	@RequestMapping("mypage/main.do")
	public String mypage_main(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("mypage_jsp","../mypage/mypage_home.jsp");
		request.setAttribute("main_jsp","../mypage/mypage_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("jjim/jjim_list.do")
	public String jjim_list(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		List<JjimVO> list = JjimDAO.jjimListData(id);
		System.out.println(list);
		
		request.setAttribute("list", list);
		
		request.setAttribute("mypage_jsp","../mypage/jjim.jsp");
		request.setAttribute("main_jsp","../mypage/mypage_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("mypage/mypage_reserve.do")
	public String mypage_reserve(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		List<ReserveVO> list = ReserveDAO.reserveListData(id);
		System.out.println(list);
		
		request.setAttribute("list", list);
		
		request.setAttribute("mypage_jsp","../mypage/reserve.jsp");
		request.setAttribute("main_jsp","../mypage/mypage_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("mypage/reserve_info.do")
	public void reserve_info(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * SELECT rno,no,rdate,rtime,inwon,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,
			f.name as fname,poster,address,parking,type,score,time,phone
		 */
		String rno = request.getParameter("rno");
		
		ReserveVO vo = ReserveDAO.reserveInfo(Integer.parseInt(rno));
		
		
		try {
			//jackson 라이브러리 => json만들어줌 (JSONObject로 안만들어도 ok)
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(vo);
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
