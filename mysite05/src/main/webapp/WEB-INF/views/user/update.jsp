<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"> </c:import>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post"
					action="${pageContext.servletContext.contextPath}/user/update">
					
					<input id="no" name="no" type="hidden" value="${userVo.no }"> 
					
					<label class="block-label" for="name">이름</label> 
					<input id="name" name="name" type="text" value="${userVo.name }"> 
					
					<label class="block-label" for="email">이메일</label> 
					${userVo.email }
					
					<label class="block-label"> 비밀번호 </label> 
					<input name="password" type="password" value="${userVo.password }">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<input type="submit" value="수정하기">

				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"> </c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"> </c:import>
	</div>
</body>
<c:if test='${param.result == "success" }'>
	<script>
		alert( "정상적으로 수정 하였습니다." );
	</script>
</c:if>
</html>