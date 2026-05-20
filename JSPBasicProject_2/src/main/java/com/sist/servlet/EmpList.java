package com.sist.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

/**
 * Servlet implementation class EmpList
 */
@WebServlet("/EmpList")
public class EmpList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//결과값을 브라우저에 어떤형식으로 전송 : html,한글
		response.setContentType("text/html;charseet=UTF-8");
		
		//어떤 메모리에 저장할지 설정 => 출력 버퍼
		PrintWriter out = response.getWriter();
		//response 안에는 해당 브라우저의 IP를 가지고 있따
		//기억: 서블릿은 각 클라이언트당 1개씩 생성 => 스레드로 되어 있다
		//DAO연결
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empListData();
		//HTML이용해서 화면 출력
		out.print("<html>");
		out.print("<body>");
		out.print("<table border=1 bordercolor=black width=500>");
		out.print("<tr>");
		out.print("<th>사번</th>");
		out.print("<th>이름</th>");
		out.print("<th>직위</th>");
		out.print("<th>입사일</th>");
		out.print("<th>급여</th>");
		out.print("<th>부서명</th>");
		out.print("<th>근무지</th>");
		out.print("</tr>");
		
		for(EmpVO vo : list) {
		out.print("<tr>");
		out.print("<td>"+vo.getEmpno()+"</td>");
		out.print("<td>"+vo.getEname()+"</td>");
		out.print("<td>"+vo.getJob()+"</td>");
		out.print("<td>"+vo.getDbday()+"</td>");
		out.print("<td>"+vo.getSal()+"</td>");
		out.print("<td>"+vo.getDvo().getDname()+"</td>");
		out.print("<td>"+vo.getDvo().getLoc()+"</td>");		
		out.print("</tr>");
		}
		out.print("</table>");
		out.print("</body>");
		out.print("</html>");
	}

}
