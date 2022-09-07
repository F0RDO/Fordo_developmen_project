<%@page import="com.poke.domain.UserChallVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.poke.domain.UserInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ChallUser</title>
</head>
<body>
<%
	UserInfoVO vo = (UserInfoVO)session.getAttribute("user");
	ArrayList<UserChallVO> list = (ArrayList)request.getAttribute("UserChall");
%>
	<h1>수행 중인 유저닉네임</h1><br>
	<%=vo.getNickname() %>
	
	<%
		for(UserChallVO vo1 : list){
			if(vo1.getCheck_chall().equals("N")){
				out.print("<h1>수행 중인 도전과제</h1><br>");
				out.print(vo1.getChallenge_name()+"<br>");	
			}else {
				out.print("<h1>수행 완료한 도전과제</h1><br>");
				out.print(vo1.getChallenge_name()+"<br>");
			}
		}
	%>
	
	
	
</body>
</html>