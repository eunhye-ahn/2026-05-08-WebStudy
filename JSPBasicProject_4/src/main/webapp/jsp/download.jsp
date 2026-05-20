<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,java.net.*"%>
<%
	String fn = request.getParameter("fn");
	String path="c:\\javaDev";
	try{
		response.setHeader("Content-Disposition", "attachment;filename=" //다운로드창
							+URLEncoder.encode(fn, "UTF-8"));
		File file = new File(path+"//"+fn);
		response.setContentLength((int)file.length());
		//프로그래스바
		//서버에서 값읽기
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		//클라에서 값
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buffer = new byte[1024];
		int i=0;
		while((i=bis.read(buffer,0,1024))!=-1){
			bos.write(buffer,0,i);
		}
		bis.close();
		bos.close();
		out = pageContext.pushBody();
	}catch(Exception e){
		
	}
%>
