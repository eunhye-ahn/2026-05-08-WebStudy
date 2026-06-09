let list = []
 window.onload=()=>{
	 //서버와 통신 axis/ajax/fetch
	 axios.get('list.do')
	 	.then(response=>{
	 		//콘솔로 값넘어오는지 확인하고 -> 레이아웃 잡고 -> 돔제어
	 		console.log(response.data)
	 		list=response.data
	 		let html=''
	 		list.forEach((m)=>{
	 			let s=''
	 			if(m.state === '상승')
	 			{
	 				s= '<font color="red">▲</font>'	
	 			}
	 			else if(m.state === '하강')
	 			{
	 				s= '<font color="blue">▼</font>'	
	 			}
	 			else
	 			{
	 				s= '-'
	 			}
	 			html+='<tr>'
	 			+'<td class="text-center">'+m.no+'</td>'
	 			+'<td class="text-center">'+s+'</td>'
	 			+'<td class="text-center"><img src="'+m.poster+'" width="30" height="30"></td>'
	 			+'<td>'+m.title+'</td>'
	 			+'<td>'+m.singer+'</td>'
	 			+'</tr>'
	 		})
	 		let tbody=document.querySelector('#user-table tbody')
	 		tbody.innerHTML=html
	 	})
	 	
	 	//객체모델 -> 태그제어
	 	let tr = document.querySelector("#user-table thead tr:first-child")
	 	tr.style.backgroundColor="orange"
	 	
	 	let key = document.querySelector("#keyword")
	 	key.addEventListener('keyup',function(){
	 		const keyword = this.value.trim()
	 		const rows=document.querySelectorAll("#user-table > tbody > tr")
	 		
	 		for(let i=0;i<rows.length;i++){
	 			rows[i].style.display='none'
	 		}
	 		rows.forEach((row)=>{
	 			//태그 안에있는 값 가져오기
	 			const title = row.querySelector("td:nth-child(4)").textContent
	 			if(title.includes(keyword)){

	 				row.style.display = ''
	 			}
	 		})
	 	})
	 	//Jquery => 바닐라JS
 }