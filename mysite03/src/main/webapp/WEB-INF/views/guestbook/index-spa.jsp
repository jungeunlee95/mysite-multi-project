<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">
body{
	margin-bottom: 30px;
}
.ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset{
	float: none;
	text-align:center
}
.ui-dialog .ui-dialog-buttonpane button {
	margin-left:10px;
	margin-right:auto;
}
#dialog-message p {
	padding:20px 0;
	font-weight:bold;
	font-size:1.0em;
}
#dialog-delete-form p {
	padding:10px;
	font-weight:bold;
	font-size:1.0em; 
}
#dialog-delete-form p.error {
	color: #f00;
}
#dialog-delete-form input[type="password"] {
	padding:5px;
	border:1px solid #888;
	outline: none;
	width: 180px;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	var isEnd = false;
	var userName = '${authUser.name}';
	/* console.log(userName); */
	
	var render = function(vo, mode=false){
		// 실제로는 template library를 사용한다. (html 렌더링 라이브러리)
		// -> ejs, underscore, mustache
		var html =  "<li data-no='"+ vo.no +"'>"+
						"<strong>"+vo.name+"</strong>"+
												// 이 문자열의 모든(g)거에 다 적용해라 . \n -> <br>
						"<p>"+ vo.contents.replace(/</gi, "&lt;").replace(/>/gi, "&gt;").replace(/\n/gi, "<br>") + "</p><br>"+
						"<strong></strong>"+
						"<a href='#' data-no='"+ vo.no +"'>삭제</a>"+ 
					"</li>"; 
		
		if(mode){			
			$("#list-guestbook").prepend(html); 			
		}else{
			$("#list-guestbook").append(html);		
		}
	} 
	 
	var fetchList = function(){
		if(isEnd == true){
			return;
		}
		
		var lastNo = $('#list-guestbook li').last().data('no') || 0;
		
		$.ajax({
			url: "${pageContext.request.contextPath}/api/guestbook/list/" + lastNo,
			type: "get",
			/* contentType: "application/json"  -> post 방식 JSON */
			dataType: "json",
			data: "",
			success: function(response){
				if(response.result != "success"){
					console.error(response.message); 
					return;
				}
				
				// detect end
				if(response.data.length == 0){
					isEnd = true; 
					$('#btn-next').prop("disabled", true);
					return; 
				}
				
				//rendering
				$.each(response.data, function(index, vo){
					render(vo);
				});
				//$.each(response.data, render); 
				
			},				// jqeury XML Http Request
			error : function(jqXHR, status, e){
				console.error(status + " : " + e);
			}
			
		});
	}
	   
	
	$(function() {
		
        var dialogDelete = $( "#dialog-delete-form" ).dialog({
            autoOpen: false,
            height: 170,
            width: 300,
            modal: true,
            buttons: {
              "삭제": function () {
            	  $.ajax({
	      				url: "${pageContext.request.contextPath}/api/guestbook/"+$("#hidden-no").val()+"/"+$("#password-delete").val(),
	      				type: "delete",
	      				//contentType: "application/json",  // -> post 방식 JSON
	      				//dataType: "json",
	      				//data:{ password : $("#password-delete").val() }, 
	      				success: function(response){ 
	      					console.log(response);
	      					
	      					if(response.result != "success"){ 
	      						console.error(response.message); 
	      						return;  
	      					} 
	      					if(response.data == false){
	      						alert("비밀번호가 올바르지 않습니다."); 
	      						$("#password-delete").val("");
	      						return;
	      					}  
	      					
	      					// li element 삭제(방명록)
	      					alert("삭제되었습니다.");
	      					$("#list-guestbook li[data-no='"+ response.message +"']").remove();
	      					dialogDelete.dialog('close'); 
	      				},	
	      				error : function(jqXHR, status, e){
	      					console.error(status + " : " + e);
	      				}
	      				
	      			});
            	}, 
              "취소" : function() {
                 dialogDelete.dialog("close");
              }
            },
            close: function() {
            	$("#password-delete").val("");
            	$("#hidden-no").val("");
            } 
          });
		
		$("#btn-next").click(function() {
			fetchList();
		});
		
		$(window).scroll(function(){
			var $window = $(this); // window가 매핑된 json 객체 
			var scrollTop = $window.scrollTop();
			var windowHeight = $window.height();
			var documentHeight = $(document).height();
			
			//console.log(scrollTop, windowHeight, documentHeight);
			if(scrollTop + windowHeight + 10 > documentHeight){
				fetchList();
			} 
		});
		
		$("#add-form").submit(function(e){
			// submit event 기본 동작을 막음
			// posting을 막음
			event.preventDefault();
			
			var vo = {};
						
			// validation (client side, UX - 클라이언트쪽에서 해도 서버에서도 반드시 해줘야함)
			// 생략  plugin 사용해서 할 수 있음 (jQuery plugin)
			vo.name = $("#input-name").val();
			vo.password = $("#input-password").val();
			vo.contents = $("#tx-content").val();
			
			// console.log( $.param(vo) ); 		    
			// => name=ff&password=ff&contents=ff   
			// console.log( JSON.stringify(vo) );        
			// => {"name":"ff","password":"ff","contents":"ff"}
			
			// 글쓰기 ajax 통신
			$.ajax({
				url: "${pageContext.request.contextPath}/api/guestbook/add",
				type: "post",
				contentType: "application/json",  // -> post 방식 JSON
				dataType: "json",
				data: JSON.stringify(vo),
				success: function(response){
					console.log(response);
					if(response.result != "success"){
						console.error(response.message);
						return;
					}
					
					//rendering
					render(response.data, true);
					
					// reset form
					$("#add-form")[0].reset();
					
				},				// jqeury XML Http Request
				error : function(jqXHR, status, e){
					console.error(status + " : " + e);
				}
				
			});
			
		}); 
		
		// Live Event => delegation(위임) 방식
		//$("#guestbook ul li a").click(function(){
		//	
		//}); // -> 이게 안먹힘
		
	    $(document).on('click', '#guestbook ul li a', function(event) {
			$anchor = $(this);
			var no = $anchor.data('no');
			$("#hidden-no").val(no); 
	    	
	    	event.preventDefault();
			dialogDelete.dialog( "open" ); 
		});  

		// 최초리스트 가져오기  
		fetchList();
	});
</script> 
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
			
				<h1>방명록</h1>
				<form id="add-form" action="/dd" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				
				<ul id="list-guestbook">
					<!-- 여기에 ajax list 놓기 -->
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
			
			<button id="btn-next">nextPage</button>
			
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>
						 			
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-spa"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html> 