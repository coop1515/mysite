<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newLine","\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board/${pagecount}?kwd=${kwd}" method="get">
					<input type="text" id="kwd" name="kwd" value="">
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
					
					<fmt:parseNumber var ='line' integerOnly="true" value = '5'/>
					<c:forEach items = '${list }' var ='vo' varStatus='status'>
					<tr>
						<td>[${total-((pagecount-1)*line)-status.index }]</td>
						<c:choose>
						<c:when test = "${vo.depth == 1}">
						<td style="text-align:left; padding-Left:0px"><a href="${pageContext.request.contextPath }/board/view/${vo.no}">${vo.title}</a></td>
						</c:when>
						<c:otherwise>
						<td style="text-align:left; padding-Left:${(vo.depth-1)*10}px"><img src='${pageContext.servletContext.contextPath }/assets/images/reply.png'/><a href="${pageContext.request.contextPath }/board/view/${vo.no}">${vo.title}</a></td>
						</c:otherwise>
						</c:choose>
						<td>${vo.name }</td>
						<td>${vo.hit}</td>
						<td>${vo.reg_date}</td>
						<c:if test = "${vo.user_no == authUser.no }">
						<td><a href="${pageContext.request.contextPath }/board/delete/${vo.no}" class="del">삭제</a></td>
						</c:if>
	
					</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${pagecount > 1}">
						<li><a href="${pageContext.request.contextPath }/board/${pagecount - 1}?kwd=${kwd}">◀</a></li>
						</c:if>
						<c:forEach begin = '1' end = '${(total/5) +1 }' step = '1' var = 'i' >
						<c:choose>
						<c:when test="${i == pagecount }">
						<li class = "selected"><a href="${pageContext.request.contextPath }/board/${i}?kwd=${kwd}">${i}</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageContext.request.contextPath }/board/${i}?kwd=${kwd}">${i}</a></li>
						</c:otherwise>
						</c:choose>
						</c:forEach>
						<c:if test="${pagecount <= total/5 }">
						<li><a href="${pageContext.request.contextPath }/board/${pagecount +1}?kwd=${kwd}">▶</a></li>
						</c:if>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
				<c:if test = "${not empty authUser}">
					<a href="${pageContext.request.contextPath }/board/write" id="new-book">글쓰기</a>
				</c:if>	
				</div>		
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>