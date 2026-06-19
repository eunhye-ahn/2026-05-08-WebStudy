package com.sist.model;

import java.util.List;

import java.io.*;
import java.net.URLEncoder;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.manager.UploadServlet;
import com.sist.vo.*;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BoardModel {
	@RequestMapping("board/list.do")
	public String board_list(HttpServletRequest request, HttpServletResponse response) {
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		final int ROWSIZE=10;
		int start = (curpage*ROWSIZE)-ROWSIZE;
		List<DataBoardVO> list = DataBoardDAO.boardListData(start);
		int count = DataBoardDAO.boardROWCOUNT();
		//정수/정수 => 정수 이므로 실수로 나누기
		int totalpage = (int)(Math.ceil(count/(double)ROWSIZE));
		count = count-((curpage*ROWSIZE)-ROWSIZE);
		
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("curpage", curpage);
		request.setAttribute("count", count);
		
		request.setAttribute("main_jsp", "../board/list.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("board/insert.do")
	public String board_insert(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../board/insert.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("board/detail.do")
	public String board_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		DataBoardVO vo = DataBoardDAO.boardDetailData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../board/detail.jsp");
		return "../main/main.jsp";
		
	}
	@RequestMapping("board/download.do")
	public void board_download(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			String fn = request.getParameter("fn");
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("")+File.separator+"uploads";
			System.out.println(path);
			File file = new File(path+File.separator+fn);
			//다운로드창띄우기
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn,"UTF-8"));
			//파일크기
			response.setContentLength((int)file.length());
			//파일복사
			//파일읽기
			BufferedInputStream bis= new BufferedInputStream(new FileInputStream(file));
			//클라이언트에 쓰기
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[1024];
			int i=0;
			//문장끝까지 읽기
			while((i=bis.read(buffer,0,1024))!=-1) {
				bos.write(buffer,0,i);
			}
			bis.close();
			bos.close();
		}catch(Exception e) {
			
		}
	}
}
