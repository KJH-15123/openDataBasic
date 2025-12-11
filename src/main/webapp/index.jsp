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
		
		document.addEventListener('DOMContentLoaded',function(){
			const btn3 = document.querySelector("#btn3");
			const tableBody = document.querySelector("table tbody");
			
			btn3.addEventListener('click',async function(){
				try{
					const response = await fetch('openData3.do',{})
					
					if(!response.ok){
						console.log(response.status);
						return;
					}
					
					const resultList = await response.json(); //JSONLIST 객체
					
					const htmlRows = resultList.map(data =>{
						return "<tr>"
  				        + "<td>" + (data.clearVal || '-') + "</td>"
				        + "<td>" + (data.sn || '-') + "</td>"
				        + "<td>" + (data.districtName || '-') + "</td>"
				        + "<td>" + (data.dataDate || '-') + "</td>"
				        + "<td>" + (data.issueVal || '-') + "</td>"
				        + "<td>" + (data.issueTime || '-') + "</td>"
				        + "<td>" + (data.clearDate || '-') + "</td>"
				        + "<td>" + (data.issueDate || '-') + "</td>"
				        + "<td>" + (data.moveName || '-') + "</td>"
				        + "<td>" + (data.clearTime || '-') + "</td>"
				        + "<td>" + (data.issueGbn || '-') + "</td>"
				        + "<td>" + (data.itemCode || '-') + "</td>"
				        + "</tr>";
					}).join('');
					
					tableBody.innerHTML = htmlRows;
					
				} catch(error){
					console.log(error)
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