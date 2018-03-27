<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Edycja użytkownika</h2>
	<f:form action="edit" method="post" modelAttribute="user">
	<f:input type="hidden" path="id" />
		<div>
			Login:
			<f:input path="login" />
			<f:errors path="login" cssClass="error" />
		</div>
		<div>
			Hasło:
			<f:input path="password" type="password"/>
			<f:errors path="password" cssClass="error" />
		</div>
		<div>
			Imię:
			<f:input path="firstName" />
			<f:errors path="firstName" cssClass="error" />
		</div>
		<div>
			Nazwisko:
			<f:input path="lastName" />
			<f:errors path="lastName" cssClass="error" />
		</div>
		<div>
			Email:
			<f:input path="email" type="email"/>
			<f:errors path="email" cssClass="error" />
		</div>
		<div>
			Numer telefonu:
			<f:input path="phoneNumber"/>
			<f:errors path="phoneNumber" cssClass="error" />
		</div>
		<div>
			<input type="submit" value="Zapisz zmiany" />
		</div>
	</f:form>
</body>
</html>