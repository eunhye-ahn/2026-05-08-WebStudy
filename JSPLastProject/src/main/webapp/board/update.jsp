<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-4.0.0.min.js"></script>
<script type="text/javascript">
$(function(){
	
	$('#update-btn').on('click',function(){
		let no = $('#no').val()
		let pwd = $('#pwd-text').val()
		let name = $('#name').val()
		let subject = $('#subject').val()
		let content = $('#content').val()
		
		$.ajax({
			method:'post',
			url:'../board/update.do',
			data:{no,name,content,subject,pwd},
			success:function(res){
				res=res.trim()
				console.log(res)
				if(res==='yes'){
					//목록 이동
					location.href="../board/detail.do?no="+no
				}else{
					alert("비밀번호가 틀렸습니다")
					$('#pwd-text').val("").focus()
					
				}
			}
		})
	})
})
</script>
</head>
<body>
<div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>글수정</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row">
            	<table class="table">
            		<tr>
            			<th class="text-center bg-success" width="15%">이름</th>
            			<td width="85%">
            				<input type="text" name="name" size="20" class="input-sm" value="${vo.name }" id="name">
            				
            			</td>
            			
            		</tr>
            		<tr>
            			<th class="text-center bg-success" width="15%">제목</th>
            			<td width="85%">
            				<input type="text" name="subject" size="60" class="input-sm" value="${vo.subject }" id="subject">
            			</td>
            			
            		</tr>
            		<tr>
            			<th class="text-center bg-success" width="15%">내용</th>
            			<td width="85%">
            				<textarea rows="10" cols="62" name="content" id="content">${vo.content }</textarea>
            			</td>
            			
            		</tr>
            		<tr>
            			<th class="text-center bg-success" width="15%">비밀번호</th>
            			<td width="85%">
            				<input type="password" name="pwd" size="10" class="input-sm" id="pwd-text" required>
            				<input type="hidden" id="pwd" value="${vo.pwd }">
            				<input type="hidden" id="no" value="${vo.no }">
            			</td>
            			
            		</tr>
            		<tr>
            			<td colspan="2" class="text-center">
            				<button type="submit" class="btn-sm btn-primary" id="update-btn">수정</button>
            				<button type="button" class="btn-sm btn-primary" onclick="javascript:history.back()">취소</button>
            			</td>
            		</tr>
            	</table>
            </div>
         </div>
     </section>
</body>
</html>