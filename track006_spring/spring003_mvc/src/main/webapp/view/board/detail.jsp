<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   

<%@include file="../inc/header.jsp"  %>
<!-- 	header		 -->
<!-- 	header		 -->
<script>
window.addEventListener("load", function(){
	let result = "${result}";
	console.log(result);
	
	if(result == "글수정 실패") { alert(result); history.go(-1); } // 알림창, 뒤로가기
	else if(result.length != 0) { alert(result); }
});
</script>

   <div class="container my-5">
      <h3>글 상세보기</h3>
      <form  action ="#"  method="post">
      	<div  class="my-3">
      		<label for="bname"   class="form-label">이름</label>
      		<input type="text"   class="form-control"    id="bname"  name="bname"  value="${board.bname}"  readonly />
      	</div>  
      	<div  class="my-3">
      		<label for="btitle"  class="form-label">제목</label>
      		<input type="text"   class="form-control"    id="btitle"  name="btitle"   value="${board.btitle}" readonly/>
      	</div>
      	<c:if test="${not empty board.bfilePath}">
      	<div  class="my-3">
      		<label for="bfilePath"  class="form-label">파일</label>
      		<img src="${pageContext.request.contextPath }/uploads/${board.bfilePath}" alt="${board.bfilePath}"/>
      	</div>
      	</c:if>
      	<div  class="my-3">
      		<label for="bcontent"  class="form-label">내용</label>
      		<textarea  class="form-control"    id="bcontent"  name="bcontent"  readonly >${board.bcontent}</textarea>
      	</div>
      	<div  class="my-3  text-end"> 
      		<a href="${pageContext.request.contextPath }/board/edit.do?bno=${board.bno}"             class="btn btn-outline-primary"  title="글수정">수정</a>
      		<a href="${pageContext.request.contextPath }/board/delete.do?bno=${board.bno}"             class="btn btn-outline-success"  title="글삭제">삭제</a>
      		<a href="${pageContext.request.contextPath }/board/list.do"             class="btn btn-primary"          title="목록보러가기">목록</a>
      	</div>
      </form> 
		 
   </div>
<!-- 	footer		 -->
<!-- 	footer		 -->
<%@include file="../inc/footer.jsp"  %>
