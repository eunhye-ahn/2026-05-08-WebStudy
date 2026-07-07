<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
   예약 / 장바구니 / 찜하기 /
   이중 include 
   관리자 페이지 / 마이 페이지 
   ---------------------
   | 부메뉴 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    

    <!-- ****** Categories Area Start ****** -->
    <section class="categories_area clearfix" id="about">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" data-wow-delay=".3s">
                        <img src="../img/catagory-img/1.jpg" alt="">
                        <div class="catagory-title">
                            <a href="../food/food_main.do">
                                <h5>맛집</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" data-wow-delay=".6s">
                        <img src="../img/catagory-img/2.jpg" alt="">
                        <div class="catagory-title">
                            <a href="#">
                                <h5>레시피</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" data-wow-delay=".9s">
                        <img src="../img/catagory-img/3.jpg" alt="">
                        <div class="catagory-title">
                            <a href="#">
                                <h5>스토어</h5>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ****** Categories Area End ****** -->

    <!-- ****** Blog Area Start ****** -->
    <section class="blog_area section_padding_0_80">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="row">
                    
                        <c:forEach var="fvo" items="${fList }">
	                        <div class="col-12 col-md-6">
	                            <div class="single-post wow fadeInUp" data-wow-delay="1s">
	                                <!-- Post Thumb -->
	                                <div class="post-thumb">
	                                    <img src="${pvo.firstimage }" alt="">
	                                </div>
	                                <!-- Post Content -->
	                                <div class="post-content">
	                                    <div class="post-meta d-flex">
	                                        <!-- Post Comment & Share Area -->
	                                        <div class="post-comment-share-area d-flex">
	                                            <!-- Post Favourite -->
	                                            <div class="post-favourite">
	                                                <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> </a>
	                                            </div>
	                                            <!-- Post Comments -->
	                                            <div class="post-comments">
	                                                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
	                                            </div>
	                                            <!-- Post Share -->
	                                            <div class="post-share">
	                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <a href="#">
	                                        <h4 class="post-headline">${pvo.title }</h4>
	                                    </a>
	                                </div>
	                            </div>
	                        </div>
						</c:forEach>
						
						
						<hr>
                        <!-- ******* List Blog Area Start ******* -->

                        <!-- Single Post -->
                        <div class="col-12">
                            <div class="list-blog single-post d-sm-flex wow fadeInUpBig" data-wow-delay=".2s">
                                <!-- Post Thumb -->
                                <div class="post-thumb">
                                    <img src="../img/blog-img/6.jpg" alt="">
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="#">By Marian</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="#">May 19, 2017</a>
                                            </div>
                                        </div>
                                        <!-- Post Comment & Share Area -->
                                        <div class="post-comment-share-area d-flex">
                                            <!-- Post Favourite -->
                                            <div class="post-favourite">
                                                <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
                                            </div>
                                            <!-- Post Comments -->
                                            <div class="post-comments">
                                                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
                                            </div>
                                            <!-- Post Share -->
                                            <div class="post-share">
                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h4 class="post-headline">${pvo.title }</h4>
                                    </a>
                                    <p>Tiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation</p>
                                    <a href="#" class="read-more">Continue Reading..</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single Post -->
                        <div class="col-12">
                            <div class="list-blog single-post d-sm-flex wow fadeInUpBig" data-wow-delay=".4s">
                                <!-- Post Thumb -->
                                <div class="post-thumb">
                                    <img src="../img/blog-img/7.jpg" alt="">
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="#">By Marian</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="#">May 19, 2017</a>
                                            </div>
                                        </div>
                                        <!-- Post Comment & Share Area -->
                                        <div class="post-comment-share-area d-flex">
                                            <!-- Post Favourite -->
                                            <div class="post-favourite">
                                                <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
                                            </div>
                                            <!-- Post Comments -->
                                            <div class="post-comments">
                                                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
                                            </div>
                                            <!-- Post Share -->
                                            <div class="post-share">
                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h4 class="post-headline">How To Get a Narcissist to Feel Empathy</h4>
                                    </a>
                                    <p>Tiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation</p>
                                    <a href="#" class="read-more">Continue Reading..</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single Post -->
                        <div class="col-12">
                            <div class="list-blog single-post d-sm-flex wow fadeInUpBig" data-wow-delay=".6s">
                                <!-- Post Thumb -->
                                <div class="post-thumb">
                                    <img src="../img/blog-img/8.jpg" alt="">
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="#">By Marian</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="#">May 19, 2017</a>
                                            </div>
                                        </div>
                                        <!-- Post Comment & Share Area -->
                                        <div class="post-comment-share-area d-flex">
                                            <!-- Post Favourite -->
                                            <div class="post-favourite">
                                                <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
                                            </div>
                                            <!-- Post Comments -->
                                            <div class="post-comments">
                                                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
                                            </div>
                                            <!-- Post Share -->
                                            <div class="post-share">
                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h4 class="post-headline">Bristol's 10 Amazing Weekend Brunches And Late Breakfasts</h4>
                                    </a>
                                    <p>Tiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation</p>
                                    <a href="#" class="read-more">Continue Reading..</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single Post -->
                        <div class="col-12">
                            <div class="list-blog single-post d-sm-flex wow fadeInUpBig" data-wow-delay=".8s">
                                <!-- Post Thumb -->
                                <div class="post-thumb">
                                    <img src="../img/blog-img/9.jpg" alt="">
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="#">By Marian</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="#">May 19, 2017</a>
                                            </div>
                                        </div>
                                        <!-- Post Comment & Share Area -->
                                        <div class="post-comment-share-area d-flex">
                                            <!-- Post Favourite -->
                                            <div class="post-favourite">
                                                <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
                                            </div>
                                            <!-- Post Comments -->
                                            <div class="post-comments">
                                                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
                                            </div>
                                            <!-- Post Share -->
                                            <div class="post-share">
                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h4 class="post-headline">10 Of The Best Places To Eat In Liverpool, England</h4>
                                    </a>
                                    <p>Tiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation</p>
                                    <a href="#" class="read-more">Continue Reading..</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <!-- ****** Blog Sidebar ****** -->
                <div class="col-12 col-sm-8 col-md-6 col-lg-4">
                    <div class="blog-sidebar mt-5 mt-lg-0">
                        <!-- Single Widget Area -->
                        <div class="single-widget-area about-me-widget text-center">
                            <div class="widget-title">
                                <h6>오늘의 날씨</h6>
                            </div>
                            <div class="about-me-widget-thumb">
                                <img src="../img/about-img/1.jpg" alt="">
                            </div>
                            <h4 class="../font-shadow-into-light">전국 곳곳 비에 무더위 계속…낮 최고 32도[오늘날씨]</h4>
                            <p>월요일인 6일은 전국이 대체로 흐린 가운데 비가 내리겠다. 기상청은 "전국에 곳에 따라 비가 내리겠으나 소강상태를 보이는 곳이 많겠다"고 이날 예보했다. </p>
                        </div>

                       

                        <!-- Single Widget Area -->
                        <div class="single-widget-area popular-post-widget">
                            <div class="widget-title text-center">
                                <h6>인기 맛집</h6>
                            </div>
                            
                            <c:forEach var="fvo" items="${fList }">
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img src="${fvo.poster }" alt="">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>${fvo.name }</h6>
                                    </a>
                                    <p>Tuesday, October 3, 2017</p>
                                </div>
                            </div>
                           </c:forEach>
                        </div>

                        <!-- Single Widget Area -->
                        <div class="single-widget-area add-widget text-center">
                            <div class="add-widget-area">
                                <img src="img/sidebar-img/6.jpg" alt="">
                                <div class="add-text">
                                    <div class="yummy-table">
                                        <div class="yummy-table-cell">
                                            <h2>Cooking Book</h2>
                                            <p>Buy Book Online Now!</p>
                                            <a href="#" class="add-btn">Buy Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Single Widget Area -->
                        <div class="single-widget-area newsletter-widget">
                            <div class="widget-title text-center">
                                <h6>Newsletter</h6>
                            </div>
                            <p>Subscribe our newsletter gor get notification about new updates, information discount, etc.</p>
                            <div class="newsletter-form">
                                <form action="#" method="post">
                                    <input type="email" name="newsletter-email" id="email" placeholder="Your email">
                                    <button type="submit"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ****** Blog Area End ****** -->
</body>
</html>