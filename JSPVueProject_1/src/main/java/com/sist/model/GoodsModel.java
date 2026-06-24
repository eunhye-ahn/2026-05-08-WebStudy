package com.sist.model;
/**
 * 
 * 응집성 => 한개에 관련된 모든 기능을 모아서 => 재사용
 * 결합성 => 수정시 다른 클래스에 영향이 가지 않도록 
 * 
 */

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.service.GoodsService;
import com.sist.service.GoodsServiceImpl;
import com.sist.vo.GoodsVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class GoodsModel {
	private GoodsService service = new GoodsServiceImpl();
	
	@RequestMapping("goods/list.do")
	public String goods_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int start = (curpage*12)-12;		//offset : 0번 / rownum : 1번
		
		List<GoodsVO> list = service.goodsListData(start);
		int totalpage = service.goodsTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		
		return "../goods/list.jsp";
	}
}
