<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./inc/header.jsp" %>
	<!-- 검색창 -->
	<div class="container card mt-3">
		<form id="searchForm" action="" method="get" onsubmit="return false;">
		  <div class="my-3">
		    <label for="search" class="form-label">검색창</label>
			  <div class="input-group">
			    <input type="text" class="form-control" id="search" name="search" onkeyup="searchKeyup()">
			  	<button type="submit" class="btn btn-primary" id="searchBtn">검색</button>
			  </div>
		  </div>
		</form>
	</div>
	<!--content-->
	<section class="container my-5">
		<h3>MultiBoard</h3>
		<table class="table table-striped table-bordered table-hover">
			<caption>BOARD 목록</caption>
			<!-- 표 제목 -->
			<thead>
				<tr>
					<th scope="col">NO</th>
					<!-- 세로 방향으로 읽어랑 -->
					<th scope="col">TITLE</th>
					<th scope="col">WRITER</th>
					<th scope="col">DATE</th>
					<th scope="col">HIT</th>
				</tr>
			</thead>
			<tbody id="ResultBody">
			<%
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/mbasic";
				Connection conn = null;
				PreparedStatement pstmt1 = null; /* PreparedStatement pstmt2 = null; */
				ResultSet rs1 = null; /* ResultSet rs2 = null; */
				
				try{
					conn = DriverManager.getConnection(url, "root", "1234");
					String sql = "SELECT ROW_NUMBER() OVER (ORDER BY BNO ASC) AS ROWNUM,"
					            +" BNO, BNAME, BTITLE, BCONTENT, BDATE, BHIT"
					            +" FROM MVCBOARD1 ORDER BY BDATE DESC";
					pstmt1 = conn.prepareStatement(sql);
					rs1 = pstmt1.executeQuery();
					
					/* pstmt2 = conn.prepareStatement("SELECT COUNT(*) `CNT` FROM MVCBOARD1");
					rs2 = pstmt2.executeQuery(); 
					int cnt = 0;
					if(rs2.next()){ cnt = rs2.getInt("CNT"); } */
					
		            /* pstmt = conn.prepareStatement(sql , 
		                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
		                    ResultSet.CONCUR_READ_ONLY); */
					
					StringBuffer sb = new StringBuffer();
					while(rs1.next()){
						int rownum = rs1.getInt("ROWNUM");
						int bno = rs1.getInt("BNO");
						String btitle = rs1.getString("BTITLE");
						String bname = rs1.getString("BNAME");
						String bdate = rs1.getString("BDATE");
						int bhit = rs1.getInt("BHIT");
						

						sb.append("<tr>");
						sb.append(String.format("<td>%d</td>",rownum));
						sb.append(String.format("<td><a href='detail.jsp?bno=%d'>%s</a></td>",bno,btitle));
						sb.append(String.format("<td>%s</td>",bname));
						sb.append(String.format("<td>%s</td>",bdate));
						sb.append(String.format("<td>%d</td>",bhit));
						sb.append("</tr>");
					}
					out.println(sb.toString());
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					/* rs2.close(); */
					rs1.close();
					/* pstmt2.close(); */
					pstmt1.close();
					conn.close();
				}
			%>
				<!-- <tr>
					<td>1</td>
					<td><a href="./detail.jsp?bno=1" title="첫번째 글쓰기">첫번째 글쓰기</a></td>
					<td>FIRST</td>
					<td>2026.05</td>
					<td><span class="badge rounded-pill bg-success">1</span></td>
				</tr>
				<tr>
					<td>2</td>
					<td><a href="./detail.jsp?bno=2" title="두번째 글쓰기">두번째 글쓰기</a></td>
					<td>FIRST</td>
					<td>2026.05</td>
					<td><span class="badge rounded-pill bg-success">3</span></td>
				</tr>
				<tr>
					<td>3</td>
					<td><a href="./detail.jsp?bno=3" title="세번째 글쓰기">세번째 글쓰기</a></td>
					<td>FIRST</td>
					<td>2026.05</td>
					<td><span class="badge rounded-pill bg-success">8</span></td>
				</tr> -->
			</tbody>
		</table>
		<!-- 글쓰기 버튼 -->
		<div class="text-end">
			<a href="./write.jsp" title="글쓰기 폼" class="btn btn-primary">글쓰기</a>
		</div>
	</section>
	
	<script>
	function searchKeyup() {
		const searchInput = document.getElementById("search").value;
		fetch("./board_select.jsp?search="+searchInput)
		  .then((res)=>{
			  if(!res.ok) throw Error("에러 코드: "+res.status);
			  return res.json();
		  })
		  .then((data)=> {
			  let htmlData = "";
			  console.log(data);
			  for(let v of data){
				  htmlData += `<tr>
				  			   <td>\${v.rownum}</td>
				  			   <td><a href=\'detail.jsp?bno=\${v.bno}\'>\${v.btitle}</a></td>
				  			   <td>\${v.bname}</td>
				  			   <td>\${v.bdate}</td>
				  			   <td>\${v.bhit}</td>
				  			   </tr>`;
			  }
			  	document.getElementById("ResultBody").innerHTML = htmlData;
		  });
	}
	</script>
<%@include file="./inc/footer.jsp" %>