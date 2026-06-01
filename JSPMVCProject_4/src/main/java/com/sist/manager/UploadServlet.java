package com.sist.manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import com.sist.dao.DataBoardDAO;
import com.sist.vo.DataBoardVO;

//insert.jsp => <form action="UploadServlet">
@WebServlet("/UploadServlet")	//URL Mapping 경로 설정
@MultipartConfig(
		fileSizeThreshold = 1024*1024, //1MB => 메모리에 저장할 최소단위 설정 임시 저장된다
		maxFileSize = 1024*1024*100, //100MB => 업로드시 허용된 실제 파일크기
		maxRequestSize = 1024*1024*50	//50MB => 요청에 포함된 모든 파일 및 데이터 전체 최대크기
)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "uploads/";
	
	//doGet => 405 (메소드방식이 다름)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = getServletContext().getRealPath("")
				+File.separator+UPLOAD_DIR;
		//File.separator => 운영체제별로 경로구분
		//window => \
		//max,ubuntu => /
		//ServletContext application = getServletContext()

		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();	//실제파일 저장할 폴더 만들기
		}
		//폴더에 파일 저장
		try {
			//1.한글깨짐방지
			request.setCharacterEncoding("UTF-8");
			//2.사용자가 보내준 데이터 받기
			String name = request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String pwd = request.getParameter("pwd");
			
			//db에 전송
			DataBoardVO vo = new DataBoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			//
			Part filePart = request.getPart("upload");
			if(filePart == null || filePart.getSize()==0) {
				//업로드가 안되는상태
				vo.setFilename("");
				vo.setFilesize(0);
			}else {
				//파일이 존재하면
				String fileName = filePart.getSubmittedFileName();
				filePart.write(uploadPath+File.separator+fileName);	//실제파일 업로드
				//db에 저장
				File f = new File(uploadPath+File.separator+fileName);
				vo.setFilename(fileName);
				vo.setFilesize((int)f.length());
			}
			//DAO연결
			DataBoardDAO.databoardInsert(vo);
			
			response.sendRedirect("databoard/list.do");
			//수정본 이름중복 흠//
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
}
