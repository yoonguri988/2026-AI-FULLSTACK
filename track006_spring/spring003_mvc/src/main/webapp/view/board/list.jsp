<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@include file="../inc/header.jsp"  %>
<!-- 	header		 -->
<!-- 	header		 -->
<script>
window.addEventListener("load", function(){
	let result = "${result}";
	console.log(result);
	
	if(result == "글쓰기 실패" 
			|| result == "글수정 실패"
			|| result == "글삭제 실패" ) { alert(result); history.go(-1); } // 알림창, 뒤로가기
	else if(result.length != 0) { alert(result); }
});
</script>
    <!--  content -->
    <section class="container  my-5">
        <h3> MultiBoard </h3>
        <pre>
        페이징: ${paging }
        전체리스트: ${list }
        </pre>
        <table  class="table  table-striped  table-bordered table-hover">
            <caption> BOARD 목록 </caption>
            <thead>
                <tr>
                    <th scope="col">NO</th>
                    <th scope="col">TITLE</th>
                    <th scope="col">WRITER</th>
                    <th scope="col">DATE</th>
                    <th scope="col">HIT</th>
                </tr>
            </thead>
            <tbody>
				<c:forEach var="dto" items="${list}" varStatus="status">
				<tr>
					<td>${paging.listtotal - (paging.current-1) * paging.onepagelist - status.index}</td>
					<td><a href="${pageContext.request.contextPath}/board/detail.do?bno=${dto.bno}" title="${dto.btitle }">${dto.btitle }</a></td>
					<td>${dto.bname }</td>
					<td>${dto.bdate }</td>
					<td>${status.index }</td>
				</tr>
				</c:forEach>
            </tbody>
            <tfoot>
            <tr><td colspan="5">
            <ul class="pagination justify-content-center">
			  <%-- 이전 버튼 --%>
			  <li class="page-item <c:if test='${!paging.hasPrev}'>disabled</c:if>">
			    <a class="page-link"
			       href="<c:choose>
			         <c:when test='${paging.hasPrev}'>?pstartno=${paging.prev}</c:when>
			         <c:otherwise>#</c:otherwise>
			       </c:choose>">이전</a>
			  </li>
        	<c:forEach var="i" begin="${paging.start }" end="${paging.end }">
    			<li class="page-item <c:if test="${paging.current == i }">active</c:if>">
    				<a href="?pstartno=${i}" class="page-link">${i}</a>
    			</li>
        	</c:forEach>
			  <%-- 다음 버튼 --%>
			  <li class="page-item <c:if test='${!paging.hasNext}'>disabled</c:if>">
			    <a class="page-link"
			       href="<c:choose>
			         <c:when test='${paging.hasNext}'>?pstartno=${paging.next}</c:when>
			         <c:otherwise>#</c:otherwise>
			       </c:choose>">다음</a>
			  </li>
        	</ul>
        	</td></tr>
            </tfoot>
        </table>
        <div class="my-5">
        </div>

        <div  class="text-end">
           <a href="${pageContext.request.contextPath}/board/write.do"  title="글쓰기 폼"  class="btn btn-primary" >글쓰기</a>
        </div>

    </section>

 
<!-- 	footer		 -->
<!-- 	footer		 -->
<%@include file="../inc/footer.jsp"  %>


<!--                 
	<tr>
                 <td>1</td>
                 <td>첫번째 글쓰기</td>
                 <td>FIRST</td>
                 <td>2026.05</td>
                 <td><span class="badge rounded-pill bg-dark">1</span></td>
             </tr> 
         -->