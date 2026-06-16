package com.sist.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.EmpDAO;
import com.sist.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class EmpModel {
	@RequestMapping("emp/if_where.do")
	public String emp_if(HttpServletRequest request, HttpServletResponse response) {
		EmpVO vo = new EmpVO();
		vo.setEname("S");
		vo.setDeptno(20);
		vo.setEmpno(0);
		
		List<EmpVO> list = EmpDAO.empFind(vo);
		
		request.setAttribute("list", list);
		
		return "../emp/if_where.jsp";
	}
	@RequestMapping("emp/if_where2.do")
	public String emp_bind(HttpServletRequest request, HttpServletResponse response) {
		
		List<EmpVO> list = EmpDAO.empFind2("B");
		
		request.setAttribute("list", list);
		
		return "../emp/if_where.jsp";
	}
	@RequestMapping("emp/if_where3.do")
	public String emp_choose(HttpServletRequest request, HttpServletResponse response) {
		
		List<EmpVO> list = EmpDAO.empFind3("SALESMAN");
		
		request.setAttribute("list", list);
		
		return "../emp/if_where.jsp";
	}
	@RequestMapping("emp/list.do")
	public String emp_list(HttpServletRequest request, HttpServletResponse response) {
		List<EmpVO> list = EmpDAO.empGetData();
		
		request.setAttribute("list", list);
		
		return "../emp/list.jsp";
	}
	@RequestMapping("emp/list_ok.do")
	public String emp_list_ok(HttpServletRequest request, HttpServletResponse response) {
		String[] data = request.getParameterValues("data");
		Map map = new HashMap();
		map.put("data", data);
		List<EmpVO> list = EmpDAO.empFind4(map);
		request.setAttribute("list", list);
		
		return "../emp/list_ok.jsp";
	}
	@RequestMapping("emp/if_where4.do")
	public String emp_trim(HttpServletRequest request, HttpServletResponse response) {
		EmpVO vo = new EmpVO();
		vo.setEname("S");
		vo.setJob("CLERK");
		List<EmpVO> list = EmpDAO.empFind5(vo);
		request.setAttribute("list", list);
		
		return "../emp/if_where.jsp";
	}
}
