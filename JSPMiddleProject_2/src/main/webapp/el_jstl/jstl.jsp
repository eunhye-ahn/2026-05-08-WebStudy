<%@page import="com.sist.vo.DataVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
List<DataVO> list = new ArrayList<>();
list.add(new DataVO("홍길동",10));
list.add(new DataVO("홍길삼",2));
list.add(new DataVO("홍길둘",20));
list.add(new DataVO("홍길넷",15));
list.add(new DataVO("홍길오",35));
list.add(new DataVO("홍길육",17));
list.add(new DataVO("홍길칠",70));
//request.setAttribute("list", list); //jstl 사용가능
%>
<!-- //자바스크립트안에서도 사용이가능하다 -->
<c:set var="list" value="<%=list %>"/>
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['이름', '로그인횟수'],
         <c:forEach var="vo" items="${list}" >
         ['<c:out value="${vo.word}"/>',<c:out value="${vo.count}"/>],
         </c:forEach>
        ]);

        var options = {
          title: '로그인 횟수 통계'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
  </body>
</html>