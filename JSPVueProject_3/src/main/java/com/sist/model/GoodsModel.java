package com.sist.model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.commons.Commons;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class GoodsModel {
	@RequestMapping("goods/list.do")
	public void goods_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		int curpage = Integer.parseInt(page);
		int start = (curpage*12)-12;
		List<GoodsVO> list = GoodsDAO.goodsListData(start);
		int totalpage = GoodsDAO.goodsTotalPage();
		
		final int BLOCK = 10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		
		Map map = new HashMap();
		map.put("goods_list", list);
		map.put("curpage", curpage);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(map);
			
			Commons.sendDate(response, "text/plain", json);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("goods/detail_vue.do")
	public String goods_detail_vue(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../goods/detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("goods/detail.do")
	public void goods_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		GoodsVO vo = GoodsDAO.goodsDetailData(Integer.parseInt(no));
		String p=vo.getGoods_price();
		p=p.replaceAll("[^0-9]", "");
		vo.setPrice(Integer.parseInt(p));
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(vo);
			
			Commons.sendDate(response, "text/plain", json);
			
			//문자열보낼때는 html (로그인성공여부 / 비밀번호 일치/불일치 등) => 항상 json으로 보내는건 아니다
			//yes / no => text/html
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void display() {
		
	}
}
