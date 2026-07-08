<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-4.0.0.min.js"></script>
<script type="text/javascript">
let i=0
$(function(){
	$('#delSpan').on('click',function(){
		if(i===0){
			i=1
			$('#delTr').show('slow')
			$('#delText').text('취소')
		}
		else{
			i=0
			$('#delTr').hide('slow')
			$('#delText').text('삭제')
		}
		
	})
	
	$('#updateBtn').on('click',function(){
		window.location.href="../board/update_ok.do?no=${vo.no}"
	})
	
	$('#delBtn').on('click',function(){
		let pwd = $('#pwd').val().trim()
		if(!pwd){
			$('#pwd').focus()
			return
		}
		let no = $('#no').text().trim()
		//alert(no)
		$.ajax({
			method:'post',
			url:'../board/delete.do',
			data:{no,pwd},
			success:function(res){
				res=res.trim()
				if(res==='yes'){
					//목록 이동
					location.href="../board/list.do"
				}else{
					alert("비밀번호가 틀렸습니다")
					$('#pwd').val("").focus()
					
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
                        <h2>상세보기</h2>
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
						<th width="20%" class="text-center bg-success text-white">번호</th>
						<td width="30%" class="text-center" id="no">${vo.no }</td>
						<th width="20%" class="text-center bg-success text-white">작성일</th>
						<td width="30%" class="text-center">${vo.dbday }</td>
					</tr>
					<tr>
						<th width="20%" class="text-center bg-success text-white">이름</th>
						<td width="30%" class="text-center">${vo.name }</td>
						<th width="20%" class="text-center bg-success text-white">조회수</th>
						<td width="30%" class="text-center">${vo.hit }</td>
					</tr>
					<tr>
						<th width="20%" class="text-center bg-success text-white">제목</th>
						<td colspan="3">${vo.subject }</td>
					</tr>
					<tr>
						<td colspan="4" class="text-left" valign="top" height="200">
							<pre style="white-space: pre-wrap;background: white;border: none;">${vo.content }</pre>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="text-right">
							<span class="btn btn-outline-warning btn-xs rounded-pill px-3" id="updateBtn">
								<i class="fa fa-pencil"></i>수정
							</span>
							<span class="btn btn-outline-info btn-xs rounded-pill px-3" id="delSpan">
								<i class="fa fa-trash"></i><span id="delText">삭제</span>
							</span>
							<span class="btn btn-outline-success btn-xs rounded-pill px-3">
								<i class="fa fa-list"></i><a href="../board/list.do">목록</a>
							</span>
						</td>
					</tr>
					<tr style="display:none" id="delTr">
						<td colspan="4" class="text-right">
							비밀번호:<input type="password" id="pwd" size="15" class="input-sm">
							<button type="button" class="btn-outline-success btn-sm" id="delBtn">
								<i class="fa fa-trash"></i>삭제
							</button>
						</td>
					</tr>
				</table>
            </div>
         </div>
     </section>
</body>
</html>