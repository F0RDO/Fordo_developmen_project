<%@page import="com.poke.domain.UserInfoVO"%>
<%@page import="com.poke.domain.CommunityVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Botanic Garden</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Website Template by gettemplates.co" />
	<meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
	<meta name="author" content="gettemplates.co" />

	<!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FreeHTML5.co
		
	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet"> -->
	<!-- <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i" rel="stylesheet"> -->
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">

	<!-- Flexslider  -->
	<link rel="stylesheet" href="css/flexslider.css">

	<!-- Owl Carousel  -->
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">

	<!-- Theme style  -->
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/style3.css">
	<link rel="stylesheet" href="css/join_style.css">

	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	<script>
	<%
		UserInfoVO user = new UserInfoVO();
		if(session.getAttribute("user")!=null){
			user = (UserInfoVO)session.getAttribute("user");
		}else {
			user = null;
		}
		CommunityVO vo = (CommunityVO)request.getAttribute("vo");
	%>
	$(document).ready(() => {
		CommentLoad(<%=vo.getPost_number() %>);
	});
	
	function CommentLoad(num){
		$.ajax({
			url : "commentLoad.do",
			type : "get",
			data : {"post_number" : num},
			dataType : "json",
			success : CommentView,
			error : () => {
				alert("error");
			}
		});
	}
	function CommentView(data){
		let list = "<table class='table table-hover' id='comment'>";
		list += "<tr style='background-color:#f5f5f5'>";
		list += "<td style='width: 15%;'>";
		list += "<h4 style='font-weight: 1000;'>닉네임</h4>"
		list += "</td>";
		list += "<td colspan='2'>";
		list += "<h4 style='font-weight: 1000;position: relative; right:65px'>댓글내용</h4>";
		list += "</td>";
		list += "</tr>";
		$.each(data, function(index, c1){
			list += "<tr>";
			list += "<td style='width: 15%;'>";
			list += "<h6>"+data[index].id+"</h6>";
			list += "</td>";
			list += "<td style='width: 70%;'>";
			list += "<h4>"+data[index].comment_contents+"</h4>";
			list += "</td>";
			list += "<td style='width: 15%;'>";
			list += "<input type='button' class='delComment' onclick=location.href='commentDelete.do?comment_num="+data[index].comment_num+"&post_number="+data[index].post_number+"' value='삭제'>";
			list += "</td>";
			list += "</tr>";
		})
		list += "</table>";
		list += "<form action='commentWrite.do'>";
		list += "<table class='table table-hover' id='commentWrite'>";
		list += "<tr>";
		list += "<td style='width: 80%;'>";
		list += "<input type='hidden' name='post_number' value='<%=vo.getPost_number() %>'>";
		let userid = "<%=user.getId() %>";
		<%
		if(user!=null){%>
			list += "<input type='hidden' name='id' value='"+userid+"'>";	
		<%}else {%>
			list += "<input type='hidden' name='id' value='fordo'>";
		<%}%>
		list += "<input type='text' name='comment_contents' class='commentWrite'>";
		list += "</td><td>";
		list += "<input type='submit' class='pull-right' id='sendComment' value='등록'>";
		list += "</td></tr>";
		list += "</table></form>";
		$("#divcheck").html(list);
	}
	</script>
	</head>
	<body>
	
	<div class="fh5co-loader"></div>
	
	<div id="page">
	<nav class="fh5co-nav" role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-md-3 col-xs-2">
					<div id="fh5co-logo"><a href="vieMain.do">Botanic Garden</a></div>
				</div>
				<div class="col-md-6 col-xs-6 text-center menu-1">
					<ul>
						<li class="has-dropdown">
							<a href="plantView.do">식물등록</a>
						</li>
						<li class="has-dropdown">
							<a href="plantCalendarView.do">일정확인</a>
						</li>
						<li><a href="viewRecommendList.do">식물추천</a></li>
						<li class="has-dropdown">
							<a href="viewPlantProblem.do">문제해결</a>
						</li>
						<li><a href="viewCommunityAll.do">커뮤니티</a></li>
					</ul>
				</div>
				<div class="col-md-3 col-xs-4 text-right hidden-xs menu-2">
					<ul>
							<li class="has-dropdown">
							<%
								if(vo!=null){
									out.print("🌻"+user.getNickname());
								}else {
									out.print("<li><a href='viewLogin.do'>LOGIN</a></li>");
								}
							%>
							<li><a href="userLogout.do">로그아웃</a></li>
							<li><a href="viewMyPage.do">마이페이지</a></li>
					</ul>
				</div>
			</div>
			
		</div>
	</nav>
	
<div class="re_full">
	<div class="wr_container">
		<input type="button" class="pull-right" id="goList" value="목록" onclick=location.href="viewCommunityAll.do"> <!--목록으로!-->
		<input type="submit" class="pull-right" id="goChange" onclick=location.href="삭제컨트롤러" value="삭제">  
		<input type="submit" class="pull-right" id="goChange" onclick=location.href="수정페이지" value="수정">  <!--세션에 있는 닉네임과 글에 있는 닉네임이 같다면 수정,삭제 버튼 나오게-->
		<table class="table table-hover" id="readContents">
				<tr>
					<td colspan="2" style="width: 85%">
						<p><%=vo.getPost_name() %></p>
					</td>
					<td style="width: 15%">
						<p>조회수 <%=vo.getView_count() %></p>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left">
						<p><%=vo.getId() %></p>
					</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3">
						<p><%=vo.getPost_contents() %></p>
					</td>
				</tr>
		</table>
		<div id="divcheck"></div>
			
				
			
</div> <!--re_container E-->
</div> <!--wr_full-->


	<!-- footer -->
	<footer id="fh5co-footer" role="contentinfo">
		<div class="container">
			<div class="row row-pb-md">
				<div class="col-md-4 fh5co-widget">
					<h3>Shop.</h3>
					<p>Facilis ipsum reprehenderit nemo molestias. Aut cum mollitia reprehenderit. Eos cumque dicta adipisci architecto culpa amet.</p>
				</div>
				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="fh5co-footer-links">
						<li><a href="#">About</a></li>
						<li><a href="#">Help</a></li>
						<li><a href="#">Contact</a></li>
						<li><a href="#">Terms</a></li>
						<li><a href="#">Meetups</a></li>
					</ul>
				</div>

				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="fh5co-footer-links">
						<li><a href="#">Shop</a></li>
						<li><a href="#">Privacy</a></li>
						<li><a href="#">Testimonials</a></li>
						<li><a href="#">Handbook</a></li>
						<li><a href="#">Held Desk</a></li>
					</ul>
				</div>

				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="fh5co-footer-links">
						<li><a href="#">Find Designers</a></li>
						<li><a href="#">Find Developers</a></li>
						<li><a href="#">Teams</a></li>
						<li><a href="#">Advertise</a></li>
						<li><a href="#">API</a></li>
					</ul>
				</div>
			</div>

			<div class="row copyright">
				<div class="col-md-12 text-center">
					<p>
						<small class="block">&copy; 2018 Free HTML5. All Rights Reserved.</small> 
						<small class="block">Designed by <a href="http://freehtml5.co/" target="_blank">FreeHTML5.co</a> Demo Images: <a href="http://blog.gessato.com/" target="_blank">Gessato</a> &amp; <a href="http://unsplash.co/" target="_blank">Unsplash</a></small>
					</p>
					<p>
						<ul class="fh5co-social-icons">
							<li><a href="#"><i class="icon-twitter"></i></a></li>
							<li><a href="#"><i class="icon-facebook"></i></a></li>
							<li><a href="#"><i class="icon-linkedin"></i></a></li>
							<li><a href="#"><i class="icon-dribbble"></i></a></li>
						</ul>
					</p>
				</div>
			</div>

		</div>
	</footer>
	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- countTo -->
	<script src="js/jquery.countTo.js"></script>
	<!-- Flexslider -->
	<script src="js/jquery.flexslider-min.js"></script>
	<!-- Main -->
	<script src="js/main.js"></script>
	<!-- Main2 -->
	<script src="js/main2.js"></script>

	</body>
</html>

    