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
	<h2>Czy potwierdzasz usunięcie użytkownika?</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Imię</th>
			<th>Nazwisko</th>
			<th>Login</th>
		</tr>
		<tr>
			<td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.login}</td>
		</tr>
	</table>
	<f:form action="delete" method="post" modelAttribute="user">
		<f:hidden path="id" />
		<input type="submit" value="Potwierdź" />
	</f:form>
	<f:form action="list" method="get">
		<input type="submit" value="Anuluj" />
	</f:form>

</body>
</html>