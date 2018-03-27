<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../jspf/header.jspf"%>
	<h1>Index</h1>
	<p>Witaj, zaloguj się jako:</p>

	<a href="user/login">Klient</a>
	<a href="driver/login">Kierowca</a>
	
	<p>Rejestracja</p>
	<a href="user/register">Klient</a>
	<a href="driver/register">Kierowca</a>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>