<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<main class="admin-main">
    <h2>📅 공지사항</h2>

    <section class="recent-section">
      <h3>📅 공지 올리기</h3>
      <form action="../notice/insert_ok.do" method="post">
	      <table>
	        <tr>
	        	<td width="20%">공지종류</td>
	        	<td width="80%">
	        		<select class="input-sm" name="type">
	        			<option value="1">서비스/점검</option>
	        			<option value="2">이벤트</option>
	        			<option value="3">정책변경</option>
	        			<option value="4">영업/운영</option>
	        			<option value="5">행사/일정</option>
	        		</select>
	        	</td>
	        </tr>
	        <tr>
	        	<td width="20%">공지제목</td>
	        	<td width="80%">
	        		<input type="text" size="30" name="subject" class="input-sm">
	        	</td>
	        </tr>
	        <tr>
	        	<td width="20%">공지내용</td>
	        	<td width="80%">
	        		<textarea rows="10" cols="33" name="content"></textarea>>
	        	</td>
	        </tr>
	        <tr>
	        	<td colspan="2" class="text-center">
	        		<button class="btn-sm btn-danger" type="submit">등록</button>
	        		<button class="btn-sm btn-primary" type="button" onclick="javascript:history.back()">취소</button>
	        	</td>
	        </tr>
	      </table>
      </form>
    </section>

  </main>
</body>
</html>