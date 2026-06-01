<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,java.net.*"%>

<%
try{
	String fn = request.getParameter("fn");
	String path = application.getRealPath("")+File.separator+"uploads"
			+File.separator+fn;
	File file = new File(path);
	//서버의 파일을 읽어서
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	//클라이언트에게 보내겠다
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	//response.getOutputStream() => 클라이언트 폴더 (다운로드)
	//1.파일명 => 파일 다이얼로그
	response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn, "UTF-8"));
	
	//2.progressbar만들기 => 파일크기 전송
	response.setContentLength((int)file.length());
	
	//실제데이터전송
	int i=0;
	byte[] buffer= new byte[1024];
	while((i=bis.read(buffer,0,1024))!=-1){
		//-1EOF
		bos.write(buffer,0,i);
	}
	
	bis.close();
	bos.close();
}catch(Exception e){
	e.printStackTrace();
}
%>