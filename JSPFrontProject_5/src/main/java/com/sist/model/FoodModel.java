package com.sist.model;

import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Model구분자 => 유일하게 컨트롤러와 연결되는 부분
 * 
 * MVC
 *  =>브라우저에서 사용자 요청 : URL (주소창)
 *  =>controller가 요청값을 받는다
 *  =>model에 등록된 메소드를 찾아서 요청 수행 완료
 *  				----------
 *  				메소드를 찾으면 자동 호출 : 어노테이션 이용
 *  				@RequestMapping => invoke()
 *  									-------
 *  									매개변수 : request/response
 *  =>완료된 내용(출력)을 JSP로 전송 
 *  	-------------------------------구조 : springframework, spring-boot
 *  									@RequestMapping
 *  											@GetMapping
 *  											@SetMapping
 *  									@Autowired / @Before @After
 *  									@Component @Repository @Service
 */
@Controller
public class FoodModel {
	@RequestMapping("food/list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		//비즈니스로직
		//Model : Model / VO / DAO / Service (자바클래스)
		
		//1.사용자요청
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*12)-12;
		
		//2.db연결
		List<FoodVO> list = FoodDAO.foodListData(start);
		
		//총페이지
		int totalpage = FoodDAO.foodTotalPage();
		//블록별
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		//1 11 21 31 ...
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		//10 20 30 ...
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		
		//db결과 request에 담기
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("list", list);
		
		return "../food/list.jsp";
	}
	
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		String[] address = vo.getAddress().split(" ");
		request.setAttribute("addr", address[2]);
		System.out.println(address[2]);
		request.setAttribute("rcount", 0);
		return "../food/detail.jsp";
	}
	
	@RequestMapping("food/list_ajax.do")
	public void foodListData_ajax(HttpServletRequest request, HttpServletResponse response) {
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*12)-12;
		
		//2.db연결
		List<FoodVO> list = FoodDAO.foodListData(start);
		
		//총페이지
		int totalpage = FoodDAO.foodTotalPage();
		//블록별
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		//1 11 21 31 ...
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		//10 20 30 ...
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		//db결과 json에 담기
		JSONArray arr = new JSONArray();	//List를 자바스크립트에서 인식하도록
		int i=0;
		System.out.println(startPage);
		
		for(FoodVO vo : list) {
			//vo와일치	=> JSONObject	=> RestFul?????
			JSONObject obj = new JSONObject();
			obj.put("no", vo.getNo());
			obj.put("poster", vo.getPoster());
			obj.put("name", vo.getName());
			//한번만 보내기 위해
			if(i==0) {
				obj.put("curpage", curpage);
				obj.put("totalpage", totalpage);
				obj.put("startPage", startPage);
				obj.put("endPage", endPage);
			}
			arr.add(obj);
			i++;
			
			
		}
		
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();	//브라우저 찾기
			out.write(arr.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
