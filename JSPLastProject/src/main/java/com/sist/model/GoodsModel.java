package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.vo.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sist.dao.*;

@Controller
public class GoodsModel {
	@RequestMapping("goods/list.do")
	public String goods_list(HttpServletRequest request, HttpServletResponse response) {
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*12)-12;
		List<GoodsVO> list = GoodsDAO.goodsListData(start);
		int totalpage = GoodsDAO.goodsTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalpage", totalpage);
		
		request.setAttribute("main_jsp", "../goods/list.jsp");
		
		//쿠키 읽어오기
		/*
		List<GoodsVO> cList = new ArrayList<GoodsVO>();
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("Goods_")) {
					String no = cookies[i].getValue();
					if(no!=null) {
						GoodsVO vo = GoodsDAO.goodsCookieData(Integer.parseInt(no));
						cList.add(vo);
					}
				}
			}
		}
		request.setAttribute("cList", cList);
		*/
		
		return "../main/main.jsp";
	}
}
