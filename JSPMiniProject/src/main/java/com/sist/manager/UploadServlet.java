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

//업로드담당
@WebServlet("/UploadServlet")
//JSP에서 실행이 안됨 = 서블릿으로 실행 => 스프링 => web.xml
@MultipartConfig(
		fileSizeThreshold = 1024*1024, //1MB => 메모리에 저장할 최소 단위 임시 저장
		maxFileSize = 1024*1024*100, //100MB => 업로드 파일 최대 크기
		maxRequestSize = 1024*1024*50 //요청에 포함된 파일 / 데이터 최대크기
)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR="uploads";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//realpath와 separator중요 !
		//separator => 파일 경로 자동변환 <-운영체제에맞게
		String uploadPath = getServletContext().getRealPath("")
				+File.separator+UPLOAD_DIR;
		//getServletContext() : JSP => application
		//application => 서버 관련 데이터 관리 => ServeltContext
		//System.out.println(uploadPath);
		//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMiniProject\\uploads
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {	//폴더가 없는 경우
			uploadDir.mkdir();
		}
		/*
		 * Part1 : file
		 * Part2 : text
		 * 
		 */
		try {
			//1. 한글깨짐 방지
			request.setCharacterEncoding("UTF-8");
			//2. 사용자가 보내준 데이터 받기
			String name = request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String pwd = request.getParameter("pwd");
			
			DataBoardVO vo = new DataBoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			//3.파일제어
			//<input type="file" name=files size=20 class="input-sm">
			Part filePart = request.getPart("files");
			if(filePart == null || filePart.getSize() == 0) {
				//파일이 업로드 안된상태
				vo.setFilename("");
				vo.setFilesize(0);
			}
			else {
				//파일업로드된상태
				//실제보낸파일 읽어오기
				String filename = filePart.getSubmittedFileName();
				//업로드완료
				filePart.write(uploadPath+File.separator+filename);
				//저장한 파일 읽어오기
				File f= new File(uploadPath+File.separator+filename);
				vo.setFilename(f.getName());
				vo.setFilesize((int)f.length());
			}
			//db저장
			DataBoardDAO.boardInsert(vo);
			//화면이동
			response.sendRedirect("board/list.do");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
