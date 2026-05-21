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
			<caption>주문현황표</caption>
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
				
				String url = "jdbc:mysql://localhost:3306/mbasic";
				String sql = "select * from milk_order order by ono desc";
				
				Connection conn = DriverManager.getConnection(url, "root", "1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
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
	<!-- 검색창 -->
	<div class="container card">
		<form id="searchForm" action="" method="get" onsubmit="return false;">
		  <div class="mb-3 mt-3">
		    <label for="search" class="form-label">검색창</label>
		    <input type="text" class="form-control" id="search" name="search" onkeyup="searchKeyup()">
		  </div>
		  <div class="mb-3 mt-3">
		  	<button type="submit" class="btn btn-primary" id="searchBtn">검색</button>
		  </div>
		</form>
		
		<div class="mt-4">
		    <table class="table table-bordered table-striped table-hover">
		        <thead>
		            <tr>
		                <th scope="col">번호</th>
		                <th scope="col">이름</th>
		                <th scope="col">수량</th>
		                <th scope="col">날짜</th>
		            </tr>
		        </thead>
		        <tbody id="resultBody">
		        </tbody>
		    </table>
		</div>
	</div>
	<script>
	function searchKeyup() {
		const searchInput = document.getElementById("search").value;
		fetch("./jsp012_select.jsp?search="+searchInput)
		  .then((res)=>{
			  if(!res.ok) throw Error("에러 코드: "+res.status);
			  return res.json();
		  })
		  .then((data)=> {
			  let htmlData = "";
			  for(let v of data){
				  htmlData += `<tr>
				  			   <td>\${v.ono}</td>
				  			   <td>\${v.oname}</td>
				  			   <td>\${v.onum}</td>
				  			   <td>\${v.odate}</td>
				  			   </tr>`;
			  }
			  	document.getElementById("resultBody").innerHTML = htmlData;
		  });
	}
/* 
    window.onload = function() {
		// onsubmit 말고 keyup
		document.getElementById("searchForm").onsubmit = function(e) {
			if(e) e.preventDefault();
			
			const searchInput = document.getElementById("search").value;
			fetch("./jsp012_select.jsp?search="+searchInput)
			  .then((res)=>{
				  if(!res.ok) throw Error("에러 코드: "+res.status);
				  return res.text();
			  })
			  .then((htmlData)=> {
				  document.getElementById("resultBody").innerHTML = htmlData;
			  });
		}
	} 
*/
	</script>
	<!-- 검색창 -->
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
	<div class="container card bg-dark text-white">
		<h2 class="card-header text-white mb-3 py-2">MILK 주문하러가기</h2>
		<div id="accordion">
		  <!-- 주문 삽입 -->
		  <div class="card my-3">
		    <div class="card-header bg-warning">
		      <a class="btn text-white" data-bs-toggle="collapse" href="#collapseOne">
		        주문 하기
		      </a>
		    </div>
		    <div id="collapseOne" class="collapse show" data-bs-parent="#accordion">
		      <div class="card-body">
				<form action="jsp012_insert.jsp" method="post" onsubmit="return checkIns()">
					<div class="my-3">
						<label for="oname">주문할 우유이름</label>
						<input type="text" name="oname" id="oname" class="form-control"/>
					</div>
					<div class="my-3">
						<label for="onum">주문할 우유갯수</label>
						<div><input type="number" name="onum" id="onum" class="form-control"/></div>
					</div>
					<div class="my-3">
						<button class="btn btn-warning text-white my-3">주문하기</button>
					</div>
				</form>
		      </div>
		    </div>
		  </div>
		  <!-- 주문 삽입 -->
		  <!-- 주문 수정 -->
		  <div class="card my-3">
		    <div class="card-header bg-warning">
		      <a class="collapsed btn text-white" data-bs-toggle="collapse" href="#collapseTwo">
		        주문 수정
		      </a>
		    </div>
		    <div id="collapseTwo" class="collapse" data-bs-parent="#accordion">
		      <div class="card-body">
				<form action="jsp012_update.jsp" method="post" onsubmit="return checkUpd()">
					<div class="my-3">
						<label for="ono1">수정할 우유번호</label>
						<input type="text" name="ono" id="ono1" class="form-control"/>
					</div>
					<div class="my-3">
						<label for="oname1">수정할 우유이름</label>
						<input type="number" name="oname" id="oname1" class="form-control"/>
					</div>
					<div class="my-3">
						<label for="onum1">수정할 우유갯수</label>
						<input type="number" name="onum" id="onum1" class="form-control"/>
					</div>
					<div class="my-3">
						<button class="btn btn-warning text-white my-3">수정하기</button>
					</div>
				</form>
		      </div>
		    </div>
		  </div>
		  <!-- 주문 수정 -->
		  <div class="card my-3">
		    <div class="card-header bg-warning">
		      <a class="collapsed btn text-white" data-bs-toggle="collapse" href="#collapseThree">
		        주문 삭제
		      </a>
		    </div>
		    <div id="collapseThree" class="collapse" data-bs-parent="#accordion">
		      <div class="card-body">
				<form action="jsp012_delete.jsp" method="post" onsubmit="return checkDel()">
					<div class="my-3">
						<label for="ono2">삭제할 우유번호</label>
						<input type="number" name="ono" id="ono2" class="form-control"/>
					</div>
					<div class="my-3">
						<button class="btn btn-warning text-white my-3">삭제하기</button>
					</div>
				</form>
				</div>
		      </div>
		    </div>
		  </div>
		</div>
<script>
function checkIns(){
	let oname = document.getElementById("oname");	
	let onum = document.getElementById("onum");	
	
	if(oname.value.trim() == ""){
		alert("우유 이름을 입력해주세요");
		return false;
	} else if(onum == "0"){
		alert("우유 갯수를 입력해주세요");
		return false;
	}
	return true;
}

function checkUpd(){
	let ono = document.getElementById("ono1");	
	let oname = document.getElementById("oname1");	
	let onum = document.getElementById("onum1");	
	
	if(ono == "0"){
		alert("우유 번호를 입력해주세요");
		return false;
	} else if(oname.value.trim() == ""){
		alert("우유 이름을 입력해주세요");
		return false;
	} else if(onum == "0"){
		alert("우유 갯수를 입력해주세요");
		return false;
	}
	return true;
}

function checkDel(){
	let ono = document.getElementById("ono");	
	
	if(ono == "0"){
		alert("우유 번호를 입력해주세요");
		return false;
	}
	return true;
}
</script>
<!-- 주문 삽입, 수정, 삭제 -->
</body>
</html>