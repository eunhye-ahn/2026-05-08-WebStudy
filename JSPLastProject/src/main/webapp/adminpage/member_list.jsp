<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.grade').on('change',function(){
		const id = $('#id').text()
		const grade = $('.grade').val()
		$.ajax({
			method:'get',
			url:'../adminpage/member_update.do',
			data:{id,grade},
			success:function(res){
				if(res>0){
					location.href="../adminpage/member_list.do"
					console.log(res)
				}else{
					alert('오류가 발생했습니다')
					console.log(res)
				}
			}
		})
	})
})
</script>
</head>
<body>
<main class="admin-main">
    <h2>📅 회원 목록</h2>

    <section class="recent-section">
    	<table class="recent-section">
    		<tr>
    			<th width="10%">아이디</th>
    			<th width="40%">이름</th>
    			<th width="10%">성별</th>
    			<th width="15%">주소</th>
    			<th width="10%">전화번호</th>
    			<th width="15%">등급</th>
    		</tr>
    		<c:forEach var="vo" items="${list }">
    			<tr>
    				<td width="10%" id="id">${vo.id }</td>
	    			<td width="40%">${vo.name }</td>
	    			<td width="10%">${vo.sex }</td>
	    			<td width="15%">${vo.addr1 }</td>
	    			<td width="10%">${vo.phone }</td>
	    			<td width="15%">
	    				<select name="grade" class="grade">
	    					<option value="1" ${vo.grade==1 ? 'checked':'' }>새싹멤버</option>
	    					<option value="2" ${vo.grade==2 ? 'checked':'' }>일반멤버</option>
	    					<option value="3" ${vo.grade==3 ? 'checked':'' }>성실멤버</option>
	    					<option value="4" ${vo.grade==4 ? 'checked':'' }>열심멤버</option>
	    					<option value="5" ${vo.grade==5 ? 'checked':'' }>핵심멤버</option>
	    				</select>
	    			</td>
    			</tr>
    		</c:forEach>
    		<tr>
    			<td colspan="6" class="text-center">
    				<a href="" class="btn btn-sm btn-primary">이전</a>
    				${curpage }page / ${totalpage } pages
    				<a href="" class="btn btn-sm btn-primary">다음</a>
    			</td>
    		</tr>
    	</table>
    </section>
</main>
</body>
</html>