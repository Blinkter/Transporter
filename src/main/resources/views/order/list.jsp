<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Lita zleceń</h2>
	<table border=1>
		<tr>
			<th>Id</th>
			<th>Początek</th>
			<th>Punkt docelowy</th>
			<th>Opis zlecenia</th>
			<th>Data przewozu</th>
			<th>Dystans</th>
			<th>Cena</th>
			<th>Data akceptacji przez kierowcę</th>
			<th>Kierowca</th>
			<th>Klient</th>
			<th>Opcje</th>
		</tr>
		<c:forEach items="${transactions}" var="transaction">
			<tr>
				<td>${transaction.id}</td>
				<td>${transaction.origin}</td>
				<td>${transaction.destination}</td>
				<td>${transaction.description}</td>
				<td><f:formatDate pattern="yyyy-MM-dd"
						value="${transaction.plannedDate }" /></td>
				<td>${transaction.distance}</td>
				<td>${transaction.price}</td>
				<td>${transaction.acceptedDate}</td>
				<td>${transaction.driver}</td>
				<td>${transaction.user.firstName} ${transaction.user.lastName}</td>
				<td><a href="<c:url value='edit?id=${transaction.id}'/>">Edytuj</a>
					<a href="<c:url value='delete?id=${transaction.id}'/>">Usuń</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>