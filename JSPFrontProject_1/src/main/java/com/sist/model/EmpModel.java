package com.sist.model;

import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 브라우저로 값전송
 * 
 */

@Controller
public class EmpModel {
	@RequestMapping("js/js_8.do")
	public void empListData(HttpServletRequest request, HttpServletResponse response) {
		List<EmpVO> list = EmpDAO.empListData();
		
		JSONArray arr = new JSONArray();		//List => [{},{},{}, ...]
		for(EmpVO vo : list) {		//VO => {}
			JSONObject obj = new JSONObject();
			obj.put("empno", vo.getEmpno());		//{"ename" : 7788, ...}
			obj.put("ename", vo.getEname());
			obj.put("job", vo.getJob());
			obj.put("dbday", vo.getDbday());
			obj.put("dname", vo.getDvo().getDname());
			obj.put("loc", vo.getDvo().getLoc());
			
			arr.add(obj);
		}
		
		//브라우저에 전송
		try {
			response.setContentType("text/plain;charset=UTF-8");	//json설정/한글설정
			PrintWriter out = response.getWriter();
			out.write(arr.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(arr.toJSONString());
	}
}
