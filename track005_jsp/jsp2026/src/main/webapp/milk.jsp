<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSP</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
  /* 기본 상태: 넘치는 콘텐츠를 숨기고 부드러운 애니메이션 적용 */
  .card-body {
    overflow: hidden;
    max-height: 500px; /* 콘텐츠가 다 담길 수 있는 충분한 높이 */
    transition: all 0.8s ease-in-out;
  }
  
  /* 닫혔을 때의 상태 */
  .card-body.collapsed {
    max-height: 0;
    padding-top: 0 !important;
    padding-bottom: 0 !important;
  }
</style>
</head>
<body>
<!-- header -->
<div class="p-5 bg-warning text-white">
  <h1>MILK ORDER Project</h1>
  <p>MVC1 - PreparedStatement EX</p>
</div>
<!-- header -->

<!-- 메뉴판테이블 -->
	<div class="container card my-5 bg-warning text-white">
		<h2 class="card-header ">Milk Menu</h2>
		<table class="table table-bordered table-striped table-hover">
			<caption>우유메뉴</caption>
			<thead class="table-dark">
				<tr>
				<th scope="col">NO</th>
				<th scope="col">NAME</th>
				<th scope="col">PRICE</th>
				</tr>
			</thead>
			<tbody>
			<%
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mbasic", "root", "1234"
				);
				PreparedStatement pstmt = conn.prepareStatement("select * from milk order by mprice asc");
				ResultSet rset = pstmt.executeQuery();
				
				StringBuffer sb = new StringBuffer();
				while(rset.next()){
					int no = rset.getInt("mno");
					String name = rset.getString("mname");
					int price = rset.getInt("mprice");
					
					sb.append("<tr>");
					sb.append("<td>"+no+"</td>");
					sb.append("<td>"+name+"</td>");
					sb.append("<td>"+price+"</td>");
					sb.append("</tr>");
				}
				
				out.println(sb.toString());
				
				if(rset != null){rset.close();}
				if(pstmt != null){pstmt.close();}
				if(conn != null){conn.close();}
			} catch(Exception e ){e.printStackTrace();}
			
			%>
			</tbody>
		</table>
	</div>
<!-- 메뉴판테이블 -->
<!-- 주문현황표 -->
	<div class="container card bg-dark text-white my-5">
		<h2 class="card-header text-white">MILK ORDER</h2>
		<table class="table table-bordered table-striped table-hover">
			<caption>우유주문내역</caption>
			<thead class="table-light">
				<tr>
				<th scope="col">NO</th>
				<th scope="col">NAME</th>
				<th scope="col">NUM</th>
				<th scope="col">주문날짜</th>
				</tr>
			</thead>
			<tbody>
			<%
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mbasic", "root", "1234"
				);
				PreparedStatement pstmt = conn.prepareStatement("select * from milk_order");
				ResultSet rset = pstmt.executeQuery();
				
				StringBuffer sb = new StringBuffer();
				while(rset.next()){
					int ono = rset.getInt("ono");
					String oname = rset.getString("oname");
					int onum = rset.getInt("onum");
					String odate = rset.getString("odate");
					
					sb.append("<tr>");
					sb.append("<td>"+ono+"</td>");
					sb.append("<td>"+oname+"</td>");
					sb.append("<td>"+onum+"</td>");
					sb.append("<td>"+odate+"</td>");
					sb.append("</tr>");
				}
				
				out.println(sb.toString());
				
				if(rset != null){rset.close();}
				if(pstmt != null){pstmt.close();}
				if(conn != null){conn.close();}
			} catch(Exception e ){e.printStackTrace();}
			
			%>
			</tbody>
		</table>
	</div>
<!-- 주문현황표 -->

<!-- create table milk_order (
  ono int not null primary key auto_increment,
  oname varchar(20)  not null,
  onum int  not null,
  odate datetime default CURRENT_TIMESTAMP,
  oip varchar(100) not null
);

desc milk_order;

-- Q1.  milk_order 값삽입.  insert 구문 완성    (oname, onum, oip)     'white' , 2,  '127.0.0.1'
insert into milk_order (oname, onum, oip) values ( 'white', 2, '127.0.0.1');
-- Q2.  milk_order ono가 1인데이터 조회 
select * from milk_order where ono = 1;
-- Q3.  milk_order 전체데이터조회
select * from milk_order;
-- Q4.  milk_order 해당번호의 이름과 갯수 수정
update milk_order
set oname = ?, onum=?
where ono = ?
-- Q5.  milk_order 해당번호의 데이터 삭제
delete from milk_order where ono = ?; -->

<!-- 주문 삽입 -->
	<div class="container card bg-dark text-white my-5">
		<h2 class="card-header text-white mb-3">MILK 주문하러가기</h2>
		<div class="card bg-white">
			<h3 class="card-header bg-warning text-white p-3" onclick="toggleOrder()">주문하기</h3>
			<div class="card-body" id="order-body">
			<form action="milk_order_process.jsp" method="post" onsubmit="return check()">
				<label for="oname">주문할 우유이름</label>
				<div><input type="text" name="oname" id="oname" class="w-100"/></div>
				<label for="onum">주문할 우유갯수</label>
				<div><input type="number" name="onum" id="onum" class="w-100"/></div>
				<button class="btn btn-warning text-white my-3">주문하기</button>
			</form>
			</div>
		</div>
	</div>
<script>
function toggleOrder(){
	const orderBody = document.getElementById('order-body');
    // collapsed 클래스를 토글(토글할 때마다 접혔다 펴짐)
    orderBody.classList.toggle('collapsed');
}

function check(){
	let oname = document.getElementById("oname");	
	let onum = document.getElementById("onum");	
	
	if(oname.value.trim() == ""){
		alert("우유값을 입력해주세요");
		return false;
	} else if(onum == "0"){
		alert("우유 갯수를 입력해주세요");
		return false;
	}
	return true;
}
</script>
<!-- 주문 삽입-->
<!-- 주문 삽입, 수정, 삭제 -->
</body>
</html>