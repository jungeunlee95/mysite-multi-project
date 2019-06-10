<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
	$(document).ready( function(){
	    $("#btn_delete").click( function() {
	        if(confirm("삭제 하시겠습니까?")) {
	            $(this).parent().click();
	        } else {
	            return false;
	        }
	    });
	});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					
					<tr>
						<td class="label">글번호</td>
						<td>${boardVo.no }</td>
					</tr>
					
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title }</td>
					</tr>
					<tr>
						<td class="label">글쓴이</td>
						<td>${boardVo.author }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
							${fn:replace(boardVo.content, newline ,"<br>")}
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath}/board?page=${fCri.page}&numPerPage=${fCri.numPerPage}&findType=${fCri.findType }&keyword=${fCri.keyword }">글목록</a>
					<c:if test="${not empty authUser }">
						<a href="${pageContext.servletContext.contextPath}/board/write/${boardVo.no }">답글</a>
					</c:if>
					<c:if test="${authUser.no eq boardVo.userNo }">
						<a href="${pageContext.servletContext.contextPath}/board/modify/${boardVo.no}">글수정</a>
						<a href="${pageContext.servletContext.contextPath}/board/delete/${boardVo.no}"
							id="btn_delete">글삭제</a>
					</c:if>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
	<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>