/**
 *js => ts 
 list_ajax.do?page=1
 */

 window.onload=()=>{
	dataRecv(1)
 }
 function foodPrint(json){
	let html = '';
	json.forEach((food)=>{
		html+='<a href="detail.do?no='+food.no+'">'
			   +'<div class="col-sm-3">'
			   +'<div class="thumbnail">'
				+'<img src="'+food.poster+'" style="width:240px;height:130px;object-fit:cover">'
				+'<p>'+food.name+'</p>'
				+'</div>'
				+'</div>'
				+'</a>'
	})
	$('#print').html(html)
	
	let curpage = json[0].curpage
	let totalpage = json[0].totalpage
	let startPage = json[0].startPage
	let endPage = json[0].endPage
	
	let pagePrint = '<ul class="pagination">'
	if(startPage>1){
		pagePrint+='<li><a class="link" onclick="prev('+(startPage-1)+')">&laquo;</a></li>'
	}
	for(let i=startPage;i<=endPage;i++){
		pagePrint+='<li><a class="link" onclick="change('+i+')">'+i+'</a></li>'
	}
	if(endPage<totalpage){
		pagePrint+='<li><a class="link" onclick="next('+(endPage+1)+')">&raquo;</a></li>'
	}
	pagePrint+='</ul>'
	
	$('#pagination').html(pagePrint)
 }
 function change(page){
	dataRecv(page)
 }
 function dataRecv(page){
	$.ajax({
			type:'POST',
			url:'list_ajax.do',
			data:{"page":page},
			//callback : 자동호출 : 결과값을 매개변수로 가져옴
			success:function(json){
				//현재문자열이들어옴 json (X)
				console.log(json) 
				//JSON변환
				json=JSON.parse(json)
				console.log(json)
				foodPrint(json)
			}
		})
 }
 function prev(page){
 	dataRecv(page)
  }
  function next(page){
  	dataRecv(page)
   }
