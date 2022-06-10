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
				<form id="search_form" action="${pageContext.request.contextPath }/board/index/${vo.page_no+1}" method="post">
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
					<c:set var='count' value='${fn:length(list) }' />
					<fmt:parseNumber var ='line' integerOnly="true" value = '5'/>
					<c:forEach items = '${list }' var ='vo' varStatus='status'>
					<c:set var='board' value='${board }' />
					<tr>
					<!-- 	<td>[${count*param.i-status.index }]</td>  -->
						<td>[${length-((param.i-1)*line)-status.index }]</td>
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
						<td>${vo.regDate}</td>
						<c:if test = "${vo.user_no == authUser.no }">
						<td><a href="${pageContext.request.contextPath }/board/delete/${vo.no}" class="del">삭제</a></td>
						</c:if>
	
					</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
				<fmt:parseNumber var ='page' integerOnly="true" value = '${length / line }'/>
					<ul>
						<c:if test="${param.i > 1 }">
						<li><a href="${pageContext.request.contextPath }/board/index&i=${param.i - 1}">◀</a></li>
						</c:if>
						<c:forEach begin = '1' end = '${page + 1 }' step = '1' var = 'i' >
						<c:choose>
						<c:when test="${i == param.i }">
						<li class = "selected"><a href="${pageContext.request.contextPath }/board/index&i=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageContext.request.contextPath }/board?a=index&i=${i}">${i}</a></li>
						</c:otherwise>
						</c:choose>
						</c:forEach>
						<c:if test="${param.i < page+1 }">
						<li><a href="${pageContext.request.contextPath }/board?a=index&i=${param.i +1}">▶</a></li>
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