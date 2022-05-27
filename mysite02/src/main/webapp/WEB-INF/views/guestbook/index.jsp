<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.douzone.mysite.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%
  	List<GuestBookVo> list = (List<GuestBookVo>)request.getAttribute("list");
  %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/guestbook?a=write" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
					<% int count = 0; for(GuestBookVo vo : list) { %>
						<table>
							<tr>
								<td><%= list.size() - count%></td>
								<td><%= vo.getName() %></td>
								<td><%= vo.getReg_date() %></td>
								<td><a href="${pageContext.request.contextPath }/guestbook?a=deleteform&no=<%= vo.getNo()%>">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
								<%= vo.getMessage().replaceAll("\n", "<br/>") %>
								</td>
							</tr>
						</table>
						<br>
						<% count++;}  %>
					</li>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>