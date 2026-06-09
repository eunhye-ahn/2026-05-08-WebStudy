<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
var IMP = window.IMP; 
IMP.init(""); 
function requestPay() {
    IMP.request_pay({
        pg: "toss",
        pay_method: "card",
        merchant_uid: "ORD20180131-0000011",   // 주문번호
        name: '선풍기' ,
        amount: 30000,         // 숫자 타입
        buyer_email: 'hong@co.kr',
        buyer_name: '안은혜',
        buyer_tel: '010-1234-5678',
        buyer_addr: '전라남도 순천시 조례동',
        buyer_postcode: '000-000'
    }, function (rsp) { // callback
    	if(rsp.success){
    	alert("구매가 완료되었습니다.\n마이페이지에서 확인하세요")
    	}
    	//window.location.href="../mypage/buy_list.do"
    	//parent.Shadowbox.close()
   });
}
</script>
</head>
<body>
	<button onclick="requestPay()">
	구매
	</button>
</body>
</html>