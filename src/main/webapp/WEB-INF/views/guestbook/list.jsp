<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.itcen.mysite.vo.GuestbookVo"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.servletContext.contextPath }/guestbook"
					method="post">
					<input type='hidden' name='a' value='add'>
					<table border=1 width=500>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" cols=60 rows=5></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<%
					pageContext.setAttribute("newline", "\n");
				%>
				<c:set var="count" value='${fn:length(list) }' />

				<c:forEach items='${list }' var='vo' varStatus='status'>
					<table>
						<tr>
							<td>[${count-(status.index+1) }]</td>
							<td>${vo.name }</td>
							<td>${vo.regDate }</td>

							<td><a
								href="${pageContext.servletContext.contextPath }/guestbook?a=deleteform&no=${vo.no}">삭제</a>
							</td>

						</tr>

						<tr>
							<td colspan=4>${fn:replace(vo.contents,newline,"<br>")}</td>

						</tr>
					</table>
				</c:forEach>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />

	</div>
</body>
</html>