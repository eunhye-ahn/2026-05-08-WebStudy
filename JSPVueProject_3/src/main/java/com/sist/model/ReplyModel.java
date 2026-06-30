package com.sist.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.commons.Commons;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.ReplyDAO;
import com.sist.vo.ReplyVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReplyModel {
	public void listData(int cno, int rno, HttpServletResponse response) {
		Map map = new HashMap();
		map.put("cno",cno);
		map.put("rno",rno);
		
		List<ReplyVO> list = ReplyDAO.replyListData(map);
		
		
		for(ReplyVO vo: list) {
			vo.setUMsg(vo.getMsg());
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(list);
			
			Commons.sendDate(response, "text/plain", json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("reply/list_vue.do")
	public void reply_list_vue(HttpServletRequest request, HttpServletResponse response) {
		String cno=request.getParameter("cno");
		String rno=request.getParameter("rno");
		
		listData(Integer.parseInt(cno), Integer.parseInt(rno), response);
	}
	
	@RequestMapping("reply/insert.do")
	public void reply_insert(HttpServletRequest request, HttpServletResponse response) {
		String cno=request.getParameter("cno");
		String rno=request.getParameter("rno");
		String msg=request.getParameter("msg");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		
		//상태관리 => 변수값유지
		//cookie(클라이언트) : 문자열 / session(서버) : Object
		
		ReplyVO vo = new ReplyVO();
		vo.setCno(Integer.parseInt(cno));
		vo.setRno(Integer.parseInt(rno));
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);
		
		System.out.println("cno"+vo.getCno());
		System.out.println("rno"+vo.getRno());
		System.out.println("msg"+vo.getMsg());
		System.out.println("id"+vo.getId());
		System.out.println("name"+vo.getName());
		
		ReplyDAO.replyInsert(vo);
		
		listData(Integer.parseInt(cno), Integer.parseInt(rno), response);
	}
	
	@RequestMapping("reply/delete.do")
	public void reply_delete(HttpServletRequest request, HttpServletResponse response) {
		String cno=request.getParameter("cno");
		String rno=request.getParameter("rno");
		String no=request.getParameter("no");
		
		ReplyDAO.replyDelete(Integer.parseInt(no));
		
		listData(Integer.parseInt(cno), Integer.parseInt(rno), response);
	}
	
	@RequestMapping("reply/update.do")
	public void reply_update(HttpServletRequest request, HttpServletResponse response) {
		String cno=request.getParameter("cno");
		String rno=request.getParameter("rno");
		String no=request.getParameter("no");
		String msg=request.getParameter("msg");
		
		ReplyVO vo = new ReplyVO();
		vo.setNo(Integer.parseInt(no));
		vo.setMsg(msg);
		
		System.out.println("no:"+no);
		System.out.println("msg:"+msg);
		
		ReplyDAO.replyUpdate(vo);
		
		listData(Integer.parseInt(cno), Integer.parseInt(rno), response);
	}
}
