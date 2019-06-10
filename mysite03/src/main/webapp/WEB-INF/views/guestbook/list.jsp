<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp">
		</c:import>
		<div id="content">
			<div id="guestbook">
				<form:form
					modelAttribute="guestbookVo"
					action="${pageContext.servletContext.contextPath}/guestbook/add"
					method="post">
					<table>
						<tr>
							<td>이름</td>
							<td>
							<c:choose>
								<c:when test='${empty authUser }'>
									<form:input path="name"/>
										<p style="font-weight: bold; color: red; text-align: left; padding: 0;">
											<form:errors path="name" />
										</p>
									</c:when>
								<c:otherwise>
								<input type="text" name="name" value="${authUser.name }" readonly>	
								</c:otherwise>
							</c:choose>
							</td>
							<td>비밀번호</td>
							<td>
								<form:password path="password"/>
								<p style="font-weight: bold; color: red; text-align: left; padding: 0;">
									<form:errors path="password" />
								</p>
							</td>
						</tr>
						<tr>
							<td colspan=4>
							<form:textarea path="contents"/>
							<p style="font-weight: bold; color: red; text-align: left; padding: 0;">
								<form:errors path="contents" />
							</p>
							</td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" value=" 확인 "></td>
						</tr>
					</table>
				</form:form>

				<c:set var='count' value='${fn:length(list) }' />
				<c:forEach items='${list }' var='vo' varStatus='status'>
					<ul>
						<li>
							<table>
								<tr>
									<td>[${count - status.index }]</td>
									<td>${vo.name }</td>
									<td>${vo.regDate}</td>
									<td><a
										href="${pageContext.servletContext.contextPath}/guestbook/delete?no=${vo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>${fn:replace(vo.contents, newline ,"<br>")}
									</td>
								</tr>
							</table> <br>
						</li>
					</ul>
				</c:forEach>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp">
		</c:import>
	</div>
</body>
</html>