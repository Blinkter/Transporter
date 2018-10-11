<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Lista użytkowników</h2>
  <table border=1>
    <tr>
        <th>Id</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Email</th>
        <th>Numer telefonu</th>
        <th>Login</th>
        <th>Hasło</th>        
        <th>Opcje</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td><a href="<c:url value='../transaction/list?id=${user.id}'/>">Zlecenia</a>
            	<a href="<c:url value='edit?id=${user.id}'/>">Edytuj</a>
            	<a href="<c:url value='delete?id=${user.id}'/>">Usuń</a></td>
        </tr>
    </c:forEach>
  </table>
  <a href="<c:url value='add'/>">Dodaj nowego użytkownika</a>
</body>
</html>