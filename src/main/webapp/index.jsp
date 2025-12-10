<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h1>공공 데이터 적용해보기</h1>
	
	<h3>미세먼지 경보 현황</h3>
	
	<button id="btn">미세먼지 경보 현황 확인</button>
	<button id="btn2">미세먼지 경보 현황 확인2</button>
	<button id="btn3">미세먼지 경보 현황 확인3</button>
	
	
	<script>
		//버튼 클릭시 api 요청 및 응답데이터 받아보기 
		$("#btn").click(function(){
			
			$.ajax({
				url : "openData.do",
				success : function(data){
					
					console.log(data);
				},
				error : function(){
					console.log("통신오류");
				}
			});
		});
		
		$("#btn2").click(function(){
			
			$.ajax({
				url : "openData2.do",
				success : function(data){
					
					console.log(data);
				},
				error : function(){
					console.log("통신오류");
				}
			});
		});
		
		$("#btn3").click(function(){
					
			$.ajax({
				url : "openData3.do",
				success : function(data){
					
					//responseStr에서 추출하고자 하는 item목록 추출
					let items = data.response.body.items;
					
					//반복문을 이용하여 tr 만들어 추가하기 
					for(let item of items){
						let tr = $("<tr>");
						tr.append($("<td>").text(item.clearVal),
								$("<td>").text(item.sn),
								$("<td>").text(item.districtName),
								$("<td>").text(item.dataDate),
								$("<td>").text(item.issueVal),
								$("<td>").text(item.issueTime),
								$("<td>").text(item.clearDate),
								$("<td>").text(item.issueDate),
								$("<td>").text(item.moveName),
								$("<td>").text(item.clearTime),
								$("<td>").text(item.issueGbn),
								$("<td>").text(item.itemCode),
								);
						
						//tr넣어주기
						$("table tbody").append(tr);
					}
					
				},
				error : function(){
					console.log("통신오류");
				}
			});
		});
	
	</script>
	<hr>
	<br>
	<table border="1">
		<thead>
			<tr>
				<th>해제시 미세먼지 농도(단위: ug/m3)</th>
				<th>미세먼지 경보 현황 일련번호</th>
				<th>발령 지역 이름</th>
				<th>발령 날짜</th>
				<th>발령시 미세먼지 농도(단위: ug/m3)</th>
				<th>발령 시간</th>
				<th>해제 날짜</th>
				<th>발령 날짜</th>
				<th>발령 권역 이름</th>
				<th>해제 시간</th>
				<th>경보단계(주의보, 경보)</th>
				<th>미세먼지 항목 구분(PM10, PM25)</th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	
	</table>
	
	
	
	
</body>
</html>