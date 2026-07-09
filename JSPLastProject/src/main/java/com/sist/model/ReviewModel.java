package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.ReviewDAO;
import com.sist.vo.ReviewVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewModel {
	@RequestMapping("review/insert.do")
	public String review_insert(HttpServletRequest request, HttpServletResponse response) {
		
		String fno=request.getParameter("fno");
		String msg = request.getParameter("msg");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		System.out.println("msg:"+msg);
		System.out.println("id:"+id);
		System.out.println("name:"+name);
		System.out.println("fno:"+fno);
		
		ReviewVO vo = new ReviewVO();
		vo.setFno(Integer.parseInt(fno));
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);
		
		ReviewDAO.reviewInsert(vo);
		
		return "redirect:../food/detail.do?no="+fno;
	}
	@RequestMapping("review/delete.do")
	public String review_delete(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String fno = request.getParameter("fno");
		
		ReviewDAO.reviewDelete(Integer.parseInt(no));
		
		return "redirect:../food/detail.do?no="+fno;
	}
	@RequestMapping("review/update.do")
	public String review_update(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String fno = request.getParameter("fno");
		String msg = request.getParameter("msg");
		
		ReviewVO vo = new ReviewVO();
		vo.setNo(Integer.parseInt(no));
		vo.setFno(Integer.parseInt(fno));
		vo.setMsg(msg);
		
		ReviewDAO.reviewUpdate(vo);
		
		return "redirect:../food/detail.do?no="+fno;
	}
}
