package com.sist.model;

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.JjimDAO;
import com.sist.vo.JjimVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class JjimModel {
	@RequestMapping("jjim/jjim_on.do")
	public String jjim_on(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		JjimVO jvo = new JjimVO();
		jvo.setFno(Integer.parseInt(fno));
		jvo.setId(id);
		JjimDAO.jjimOn(jvo);
		
		return "redirect:../food/detail.do?no="+fno;
	}
	@RequestMapping("jjim/jjim_off.do")
	public String jjim_off(HttpServletRequest request, HttpServletResponse response) {
		String jno = request.getParameter("jno");
		
		JjimDAO.jjimOff(Integer.parseInt(jno));
		
		return "redirect:../jjim/jjim_list.do";
	}
}
