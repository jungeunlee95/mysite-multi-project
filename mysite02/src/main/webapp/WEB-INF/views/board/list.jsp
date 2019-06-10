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
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<Script>
   var result = getParameterByName('message'); 
   
   if (result == 'success') {
      alert("게시글이 삭제 되었습니다!");
      location.href='${pageContext.servletContext.contextPath}/board' 
   }
   
   if(result == 'fail') {
      alert("다시 시도해주세요");
      location.href='${pageContext.servletContext.contextPath}/board'
   }

   function getParameterByName(name) {
       name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
       var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
           results = regex.exec(location.search);
       return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
   }
</Script>
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"> </c:import>
		<div id="content">
			<div id="board">
			 
			
				<form id="search_form" action="${pageContext.servletContext.contextPath}/board${pagingMaker.makeURI(1)}" method="post">
					<input type="text" id="keyword" name="keyword" value="${fCri.keyword}">
					<input type="hidden" id="findType" name="findType" value="T">
					<input type="submit" value="찾기">
				</form>
				
				
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
				<c:set var='count' value='${fn:length(list) }'/>
				<c:forEach items='${list }' var='vo' varStatus='status'>		
					<tr>
						<td>[${count - status.index }]</td>
						<c:if test="${vo.depth == 0}">
							<td style="text-align: left;">
								<a href="${pageContext.servletContext.contextPath}/board/view/${vo.no}${pagingMaker.makeFind(pagingMaker.cri.page)}">${vo.title }</a>
							</td>
						</c:if>
						<c:if test="${vo.depth != 0}">
							<td style="text-align: left; padding-left: ${15*vo.depth}px;">
								<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png">
								<a href="${pageContext.servletContext.contextPath}/board/view/${vo.no}${pagingMaker.makeFind(pagingMaker.cri.page)}">${vo.title }</a>
							</td>
						</c:if> 
						<td>${vo.author }</td>   
						<td>${vo.viewCount }</td>
						<td>${vo.regDate}</td> 
					</tr>
				</c:forEach>	
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${pagingMaker.prev}">
							<li><a href="${pageContext.servletContext.contextPath}/board${pagingMaker.makeFind(pagingMaker.startPage-1) }">◀</a></li>
						</c:if>
						
						<c:forEach begin="${pagingMaker.startPage}" end="${pagingMaker.endPage}" var="pNum">
							<li><a href="${pageContext.servletContext.contextPath}/board${pagingMaker.makeFind(pNum) }" >
							${pNum}</a></li>	
						</c:forEach>
						
						<c:if test="${pagingMaker.next&&pagingMaker.endPage>0}">
							<li><a href="${pageContext.servletContext.contextPath}/board${pagingMaker.makeFind(pagingMaker.endPage+1) }">▶</a></li>
						</c:if>
					</ul>
				</div>		 			
				<!-- pager 추가 -->
				
				
				<c:if test="${not empty authUser }">
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath}/board/write" id="new-book">글쓰기</a>
					</div>	
				</c:if>
							
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"> 
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"> </c:import>
	</div>
</body>
</html>