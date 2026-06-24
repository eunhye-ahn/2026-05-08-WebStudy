package com.sist.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.io.PrintWriter;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {
	@RequestMapping("food/list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		
		
		return "../food/list.jsp";
	}
	@RequestMapping("food/list_vue.do")
	public void food_list_vue(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = request.getParameter("page");
			int curpage = Integer.parseInt(page);
			int start = (curpage*12)-12;
			List<FoodVO> list = FoodDAO.foodListData(start);
			int totalpage = FoodDAO.foodTotalPage();
			
			final int BLOCK = 10;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage) {
				endPage=totalpage;
			}
			
			//jackson은 자동으로 
			//@RestController : 자동 jackson사용 => JSON
			Map map = new HashMap();
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("curpage", curpage);
			map.put("totalpage", totalpage);
			map.put("food_list", list);
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(map);
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("food/detail_vue.do")
	public void food_detail_vue(HttpServletRequest request, HttpServletResponse response) {
		try {
			String no = request.getParameter("no");
			FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(no));
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(vo);
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * <select id="foodFindData" resultType="FoodVO" parameterType="hashmap">
		SELECT *
		FROM food
		WHERE #{column} LIKE '%'||#{ss}||'%'
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	</select>
	 */
	@RequestMapping("food/find_vue.do")
	public void food_find_vue(HttpServletRequest request, HttpServletResponse response) {

			String ss = request.getParameter("ss");
			String column = request.getParameter("column");
			String page = request.getParameter("page");
			int curpage = Integer.parseInt(page);
			int start = (curpage*12)-12;
			
			Map map = new HashMap();
			map.put("ss", ss);
			map.put("column",column);
			map.put("start",start);
			
			List<FoodVO> list = FoodDAO.foodFindData(map);
			int totalpage = FoodDAO.foodFindTotalPage(map);
			
			final int BLOCK=10;
			 int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			 int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			  
			 if(endPage>totalpage)
				 endPage=totalpage;
			 
			 map = new HashMap();
			 map.put("startPage", startPage);
			  map.put("endPage", endPage);
			  map.put("curpage", curpage);
			  map.put("totalpage", totalpage);
			  System.out.println(totalpage);
			  map.put("food_list", list);
			  
			  //map 전송 : json 제작 -jackson활용 (vue) ????
			  try {
				  ObjectMapper mapper = new ObjectMapper();
				  String json = mapper.writeValueAsString(map);
				  
				  response.setContentType("text/plain;charset=UTF-8");
				  PrintWriter out = response.getWriter();
				  out.write(json);
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
	}

	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		
		return "../food/detail.jsp";
	}
}
