<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${list.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">${list.contents }</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<c:if test="${authUser!=null}">
						<a
							href="${pageContext.servletContext.contextPath }/board?a=writeform&flag=true&gNo=${list.gNo }&oNo=${list.oNo }&depth=${list.depth }">답글달기</a>

					</c:if>
					
					<a href="${pageContext.servletContext.contextPath }//board?kwd=">글목록</a>
					<c:if test="${(list.userNo==authUser.no) && (authUser!=null)}">
						<a
							href="${pageContext.servletContext.contextPath }/board?a=modifyform&no=${list.no}&title=${list.title }&content=${list.contents }">글수정</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>