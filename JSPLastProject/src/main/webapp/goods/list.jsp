<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/cookie.css">
</head>
<body>
    <section class="archive-area section_padding_80">
        <div class="container" style="width:1024px">
            <div class="row">
            <c:forEach var="vo" items="${list}">
                <!-- Single Post -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-post wow fadeInUp" data-wow-delay="0.7s">
                        <!-- Post Thumb -->
                        <div class="post-thumb">
                            <img src="${vo.goods_poster }" alt="">
                        </div>
                        <!-- Post Content -->
                        <div class="post-content">
                            <div class="post-meta d-flex">
                                <div class="post-author-date-area d-flex">
                                    <!-- Post Author -->
                                    <div class="post-author">
                                        <a href="#">${vo.goods_first_price }</a>
                                    </div>
                                    <div class="post-date">
                                        <a href="#">${vo.goods_price }</a>
                                    </div>
                                    <!-- Post Date -->
                                </div>
                                <!-- Post Comment & Share Area -->
                                <div class="post-comment-share-area d-flex">
                                    <!-- Post Favourite -->
                                    <div class="post-favourite">
                                        <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> ${vo.like_count }</a>
                                    </div>
                                    <!-- Post Comments -->
                                    <div class="post-comments">
                                        <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> ${vo.reply_count }</a>
                                    </div>
                                    <!-- Post Share -->
                                    <div class="post-share">
                                        <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                    </div>
                                </div>
                            </div>
                            <a href="../goods/detail.do?no=${vo.no }">
                                <h4 class="post-headline">${vo.goods_name }</h4>
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
	                                <li class="page-item active">
	                                    <a class="page-link" href="../goods/list.do?page=${startPage-1 }"><span class="sr-only"></span>이전</a>
	                                </li>
                                </c:if>
                                <c:forEach var="i" begin="${startPage }" end="${endPage }">
                                <li class="page-item ${i==curpage ? 'active':''}"><a class="page-link" href="../goods/list.do?page=${i}">${i }</a></li>
                                </c:forEach>
                                <c:if test="${endPage<totalpage }">
                                <li class="page-item">
                                    <a class="page-link" href="../goods/list.do?page=${endPage+1 }">다음 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
                                </li>
                                </c:if>
                            </ul>
                        </nav>
                        <div class="page-status">
                            <p>Page ${curpage } of ${totalpage } Pages</p>
                        </div>
                    </div>
                </div>
                

	             
            </div>
        </div>
    </section>
</body>
</html>