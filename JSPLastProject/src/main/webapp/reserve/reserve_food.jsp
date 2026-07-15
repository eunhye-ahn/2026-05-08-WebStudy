<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script type="text/javascript" src="http://code.jquery.com/jqeury-4.0.0.min.js"></script>
  <script type="text/javascript">
  	$(function(){
  		$(".types").on('click',function(){
  			let type = $(this).text()
  			$.ajax({
  				method:'post',
  				url:'../reserve/reserve_food.do',
  				data:{type},
  				success:function(res){
  					$('#food_list').html(res)
  				}
  			})
  		})
  		$('.btns').on('click',function(){
  			let type = $(this).attr('data-type')
  			let page = $(this).attr('data-page')
  			$.ajax({
  				method:'post',
  				url:'../reserve/reserve_food.do',
  				data:{type,page},
  				success:function(res){
  					$('#food_list').html(res)
  				}
  			})
  			
  		})
  		$('.food-item').on('click',function(){
		  let no=$(this).attr("data-no")
		  let poster=$(this).attr("data-poster")
		  let name=$(this).attr("data-name")
		  $('#food_poster').attr("src",poster)
		  $('#food_name').text(name)
		  $('#food_poster').show()
		  $('.table-borderless').show()
		  $('#rno').val(no)
		  
		  $.ajax({
			  type:'post',
			  url:'../reserve/reserve_date.do',
			  success:function(res){
				  $('#food_rdays').html(res)
			  }
		  })
	  })
  	})
  </script>
</head>
<body>
	<table class="table">
		<tr>
			<td class="text-center">
				<button type="button" class="btn-xs btn-danger types">한식</button>
				<button type="button" class="btn-xs btn-primary types">양식</button>
				<button type="button" class="btn-xs btn-warning types">중식</button>
				<button type="button" class="btn-xs btn-info types">일식</button>
				<button type="button" class="btn-xs btn-success types">분식</button>
			</td>
		</tr>
	</table>
	<table class="table table-hover align-middle">
		<tbody>
			<c:forEach var="vo" items="${list }">
				<tr class="food-item" data-poster="${vo.poster }" data-name="${vo.name }" data-no="${vo.no }">
					<td><img src="${vo.poster }" class="rounded" style="width:150px;height:100px"></td>
					<td>
						<strong>${vo.name}</strong><br>
						<small>${vo.type }</small>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2" class="text-center">
					<button class="btn btn-success btn-sm btns" type="button" data-page="${curpage>1 ? curpage-1 : curpage }" data-type="${vo.type }">이전</button>
					${curpage } page/ ${totalpage } pages
					<button class="btn btn-success btn-sm btns" type="button" data-page="${curpage<totalpage ? curpage+1 : curpage }" data-type="${vo.type }">다음</button>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>