<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="navigation">
	<ul>
		<c:choose>
			<c:when test='${param.menu == "main" }'>
				<li class="selected"><a href="${pageContext.servletContext.contextPath}/">고양이</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/guestbook">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/board">게시판</a></li>
			</c:when>
			<c:when test='${param.menu == "guestbook" }'>
				<li><a href="${pageContext.servletContext.contextPath}/">고양이</a></li>
				<li class="selected"><a href="${pageContext.servletContext.contextPath}/guestbook">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/board">게시판</a></li>
			</c:when>
			<c:when test='${param.menu == "board" }'>
				<li><a href="${pageContext.servletContext.contextPath}/">고양이</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/guestbook">방명록</a></li>
				<li class="selected"><a href="${pageContext.servletContext.contextPath}/board">게시판</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.servletContext.contextPath}/">고양이</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/guestbook">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/board">게시판</a></li>
			</c:otherwise>
		</c:choose>
		<c:if test="${authUser.role == 'ADMIN' }">
			<li><a href="${pageContext.servletContext.contextPath}/admin">관리자페이지</a></li> 
		</c:if>
	</ul>
</div> 