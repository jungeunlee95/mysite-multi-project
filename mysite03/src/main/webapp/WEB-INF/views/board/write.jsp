<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form:form 
					modelAttribute="boardVo"
					class="board-form" method="post" 
					action="${pageContext.servletContext.contextPath}/board/write">				
					
					<c:if test="${not empty groupNo }">
						<input type='hidden' name="groupNo" value="${groupNo }">
					</c:if>
					<input type='hidden' name="userNo" value="${authUser.no }">
					<p style="font-weight: bold; color: red;text-align: left; padding: 0;">
						<form:errors path="userNo"/>
					</p>
					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						
						<tr>
							<td class="label">제목</td>
							<td>	
								<form:input path="title"/>
								<p style="font-weight: bold; color: red;text-align: left; padding: 0;">
									<form:errors path="title"/>
								</p>
							</td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<form:textarea path="content"/>
								<p style="font-weight: bold; color: red;text-align: left; padding: 0;">
									<form:errors path="content"/>
								</p>
							</td>
						</tr>
						
					</table>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath}/board?page=${fCri.page}&numPerPage=${fCri.numPerPage}&findType=${fCri.findType }&keyword=${fCri.keyword }">취소</a>
						<input type="submit" value="등록">
					</div>
				</form:form>				
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
	<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>