<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/timeline.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(document).ready(function(){
	  var modalLayer = $("#modalLayer");
	  var modalLink = $(".modalLink");
	  var modalCont = $(".modalContent");
	  var marginLeft = modalCont.outerWidth()/2;
	  var marginTop = modalCont.outerHeight()/2; 

	  modalLink.click(function(){
	    modalLayer.fadeIn();
	    modalCont.css({"margin-top" : -marginTop, "margin-left" : -marginLeft});
	    $(this).blur();
	    $(".modalContent > a").focus(); 
	    return false;
	  });

	  $(".modalContent > button").click(function(){
	    modalLayer.fadeOut();
	    modalLink.focus();
	  });		
	});
</script>
</head>
<body>
<!-- 삭제 모달 -->
<div id="modalLayer">
  <div class="modalContent">
    <div class="modal-title"> 메시지 삭제 </div>
    <h4>작성시 입력했던 비밀번호를 입력하세요.</h4>
    <input type="text"> 
    <button type="button" class="modal-btn1">X</button> 
	<button type="button" class="modal-btn2">삭제</button> 
	<button type="button" class="modal-btn3">취소</button>
  </div>
</div>

	<div id="container"> 
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<div class="logo-title">	
				<img class="logo" src="/assets/images/guestbook.png">	
					<h1 id="master-title">방명록</h1>
				</div>
				<form id="add-form" action="" method="post"> 
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea> 
					<input id="submit" type="submit" value="보내기" /> 
				</form>
				<hr>
				<ul id="list-guestbook">
					<li data-no=''>
						<strong class="title">지나가다가</strong>
						<p class="content">
							별루입니다.<br>
							비번:1234 -,.-
						</p>
						<strong class="profile"
						style="background-image:url('/assets/images/user.png');">사진</strong>
						
						<a href='#modalLayer' data-no='' id="btn_delete" class="modalLink"
						style="background-image:url('/assets/images/delete.png');">삭제</a> 
					</li>
					<li data-no=''>
						<strong class="title">지나가다가</strong>
						<p class="content">
							별루입니다.<br>
							비번:1234 -,.-
						</p>
						<strong class="profile"
						style="background-image:url('/assets/images/user.png');">사진</strong>
						<a href='#modalLayer' data-no='' id="btn_delete" class="modalLink"
						style="background-image:url('/assets/images/delete.png');">삭제</a> 
					</li>
					<li data-no=''>
						<strong class="title">지나가다가</strong>
						<p class="content">
							별루입니다.<br>
							비번:1234 -,.-
						</p>
						<strong class="profile"
						style="background-image:url('/assets/images/user.png');">사진</strong>
						<a href='#modalLayer' data-no='' id="btn_delete" class="modalLink"
						style="background-image:url('/assets/images/delete.png');">삭제</a> 
					</li>
					<li data-no=''>
						<strong class="title">지나가다가</strong>
						<p class="content">
							별루입니다.<br>
							비번:1234 -,.-
						</p>
						<strong class="profile"
						style="background-image:url('/assets/images/user.png');">사진</strong>
						<a href='#modalLayer' data-no='' id="btn_delete" class="modalLink"
						style="background-image:url('/assets/images/delete.png');">삭제</a> 
					</li>
				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="timeline"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>