<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
	저장공간
	session : 접속시에 서버에 저장 => 공간
				한명당 1개만 생성
				| port가 다른 경우에는 저장할 수 없다
				react / spring boot
				3000		8080
				|=>javascript의 session
				|=>JWT:cookie기반
					--- 카카오로그인 등 소셜로그인 가능
			=> 사용자정보저장 (로그인)
			=> 브라우저 종료 / 로그아웃 시에 자동삭제 -> 세션으로 자동로그인 구현X
			=> 내장객체 HttpSession에서 생성
			=> Object단위로 저장
			=> 주요 메소드 
				1) setAttribute(String key, Object value)
					저장시에 사용
				2) getAttribute(String key)
					=> 출력시 ${sessionScope.key}
				3) invalidate() : 세션해제 => 전체메모리 해제
								로그아웃/브라우저종료
				4) isNew() : 처음 저장하는 여부 확인
							=> 장바구니 
				5) getId() : 구분 (사용자)
					|=> 채팅
				6) setMaxInactiveInterval() : 시간 설정
					|=>18000초(30분)
	cookie : 브라우저에 저장
				new Cookie()
				=> 문자열만 가지고 있다
				=> 브라우저를 종료해도 남아있다
				=> 자동로그인 / 최근방문 / 장바구니
				=> 주요메소드
					1) 저장 => 생성자 new Cookie(String key, String value)
					2) setPath("/") => 저장위치 지정
 					3) cookie => key읽기 : getName()
 								value읽기 : getValue()
 					4) 삭제 : setMaxAge(0)
 	cookie / session => 상태관리프로그램
 						------데이터유지 (vue/react)
 	=> 생성
 		request를 이용해서 생성
 		request.getSession() request.getCookies()
 		-------
 		getParameter() : 요청값 받기 (1개) => return은 무조건 String
 		getParameterValues() : 여러개를 동시에 받는 경우 => String[]
 		setAttribute() : request안에 새로운 데이터를 저장
 			=> getAttribute() : => JSP : ${key}
 		response : 응답
 			=> HTML => setContextType("text/html")
 										text/xml
 										text/plain :JSON
 			=> COOKIE => addCookie()
 			=> setHeader()
 			=> sendRedirect()
 		application : 서버관리
 			=> getRealPath() / getResource()
 								---------- 운영체제와 관계없이 사용 가능
 		MVC
 			브라우저 : .do
 				|  request
 			DispatcherServlet (Controller)
 				|  request
 		   	  Model <======> DAO
 				|  request안에 출력에 필요한 데이터를 request에 담는다
 			 DispatcherServlet (Controller)
 				|  request
 			   JSP(해당)
 			   
 	----------------------------------------------------------------------------		   
 	MyBatis 
 	=> 환경설정 파일 (Config.xml) : 한개만 생성
		| Connection 관련
	=> SQL 저장 파일 (mapper.xml) : 여러개 생성이 가능 (테이블당 1개씩 생성)
		| DAO-
			= selectList("id명",매개변수) => List
								------ #{}
								한개만 지정이 가능
								--------------
								#{}이 여러개인 경우
								=> hashmap / VO
									-------  ---
											#{id} -> vo.getId()
									#{id}
									-----key
									map.get("id")
			= selectOne
			= insert
			= update
			= delete
			
			XML구사
				id => 구분자 (모든 mapper에서 id를 중복하면 오류 발생)
							 primary / key
							 => 테이블명
				resultType : SQL실행 후에 결과값을 받는 변수 (VO), int, string
				parameterType : ?에 값을 채우는 데이터형 #{}
				resultMap : join / 컬럼명이 다른 경우에 설정
				parameterMap : 프로시저 사용시에 변수 설정
					=> IN/OUT/INOUT
					=> 전체 댓글 사용
				create procedure pro_name(pName VARCHAR(10), ...)
				| 반복이 많은경우/보안/트랜잭션
				
			접속
				Session session = ssf.openSession() : SELECT
									=> commit : false
								 ssf.openSession(true) : DML(insert,update,delete)
								 	=> commit : true
				session.close():반환 => 재사용
				
			Jquery
				=> $('태그명, ID명, 클래스명, 선택자')
					----------------------------- 태그 가지고오는 경우 (DOM)
					값읽기
						=> val() : input / select / textarea
						=> text() : <태그>값</태그>
						=> html() : <태그>html</태그>
						=> attr() : 속성 값 읽기
						=> append()
					이벤트
						=> click : $().on('click',function(){})
						=> change 
						=> keyup
						=> mousedown
						=> hover
					효과
						=> show
						=> hide
						=> fadeIn / fadeOut
					서버 연결
						$.ajax({
							type:'get/post',
							url:'food/list.do',
							data:{"page":1}, => JSP로 값 전송
							success:function(response){정상수행 => 결과값 출력},
							error:function(error){에러처리}
						})
						fetch => 내장함수
						axios() => 가장 많이 사용 : Vue/React
						
					Jquery : Javascript
					JSP : java
					DAO : SQL
 --%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>맛집 목록</h2>
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
                            <li class="breadcrumb-item"><a href="#"><i class="fa fa-home" aria-hidden="true"></i></a></li>
                            <li class="breadcrumb-item active" aria-current="page"></li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row">
            
            <c:forEach var="vo" items="${list }">
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-post wow fadeInUp" data-wow-delay="0.1s">
                        <!-- Post Thumb -->
                        <div class="post-thumb">
	                        <a href="../food/detail_before.do?no=${vo.no }">
	                            <img src="${vo.poster }" alt="">
	                        </a>
                        </div>
                        <!-- Post Content -->
                        <div class="post-content">
                            <div class="post-meta d-flex">
                                <div class="post-author-date-area d-flex">
                                    <!-- Post Author -->
                                    <div class="post-author">
                                        <a href="#">${vo.type }</a>
                                    </div>
                                    <!-- Post Date -->
                                    <div class="post-date">
                                        <a href="#">${vo.phone }</a>
                                    </div>
                                </div>
                                <!-- Post Comment & Share Area -->
                                <div class="post-comment-share-area d-flex">
                                    <!-- Post Favourite -->
                                    <div class="post-favourite">
                                        <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> ${vo.likecount }</a>
                                    </div>
                                    <!-- Post Comments -->
                                    <div class="post-comments">
                                        <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> ${vo.replycount }</a>
                                    </div>
                                    <!-- Post Share -->
                                    <div class="post-share">
                                        <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                    </div>
                                </div>
                            </div>
                            <a href="../food/detail_before.do?no=${vo.no }">
                                <h4 class="post-headline">${vo.name }</h4>
                            </a>
                        </div>
                    </div>
                </div>
                </c:forEach>
                
                <div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                            	<c:if test="${startPage>1 }">
	                            	<li class="page-item">
	                                    <a class="page-link" href="../food/list.do?page=${startPage-1 }">이전 <i class="fa fa-angle-double-left" aria-hidden="true"></i></a>
	                                </li>
                                </c:if>
                                
                                <c:forEach var="i" begin="${startPage }" end="${endPage }">
                                	<li class="page-item ${curpage==i? 'active':'' }"><a class="page-link" href="../food/list.do?page=${i }">${i }</a></li>
                                </c:forEach>
                                
                                <c:if test="${endPage<totalpage }">
	                                <li class="page-item">
	                                    <a class="page-link" href="../food/list.do?page=${endPage+1 }">다음 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
	                                </li>
                                </c:if>
                            </ul>
                        </nav>
                        <div class="page-status">
                            <p>Page ${curpage } of ${totalpage } results</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>
</body>
</html>