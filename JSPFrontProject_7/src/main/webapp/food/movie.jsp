<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.row{
	margin: 0px auto;
	width:960px;
	
}
p{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#findBtn').on('click',function(){
		let fd = $('#fd').val()
		if(fd.trim()===''){
			$('#fd').focus()
			return
		}
		search(fd)
	})
})
const search = async(fd) => {
	try{
		//fetch => 비동기로 받는다 (jquery에서는 ajax) : react/vue - fetch
		const response = await fetch('https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=28&q='+fd+'&type=video&key=AIzaSyAEUyg8mhVTkQf-F6kw3893AHqanrCJxoQ')
		const result = await response.json()
		const movie = result.items;
		console.log(movie)
		let html = '';
		movie.forEach((m)=>{
			html+='<div class="col-sm-4">'
					+'<div class="thumbnail">'
					+'<embed src="http://www.youtube.com/embed/'+m.id.videoId+'">'
					+'</div>'
					+'<p>'+m.snippet.title+'</p>'
					+'</div>'
		})
		$('#print').html(html)
	}catch(error){
		console.log("error",error)
	}
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<input type="text" size="20" class="input-sm" id="fd" value="">
			<button type="button" id="findBtn" class="btn-sm btn-primary">검색</button>
		</div>
		<div class="row" id="print" style="margin-top:30px">
			
		</div>
	</div>
</body>
