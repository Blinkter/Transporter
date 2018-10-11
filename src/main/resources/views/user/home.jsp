<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../jspf/header.jspf"%>
	<h1>Panel użytkownika</h1>

	<h2>
		<a href="<c:url value='../transaction/add'/>">Zleć przesyłkę</a>
	</h2>
	<h2>
		<a href="<c:url value='../transaction/userlist?id=${user.id}'/>">Sprawdź
			status zamówienia</a>
	</h2>

	<%-- <f:form action="list" method="get" modelAttribute="transaction">
		<f:hidden path="id" />
		<input type="submit" value="status" />
	</f:form> --%>
</body>
</html>