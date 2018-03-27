<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Nowe zamówienie </h2>
	<f:form method="post" modelAttribute="transaction">
	<f:hidden path="user.id" value="${user.id}"/>
		<div>
			Początek:
			<f:input path="origin" />
			<f:errors path="origin" cssClass="error" />
		</div>
		<div>
			Punkt docelowy:
			<f:input path="destination" />
			<f:errors path="destination" cssClass="error" />
		</div>
		<div>
			Opis zlecenia:
			<f:textarea path="description" cols="30" rows="4" />
			<f:errors path="description" cssClass="error" />
		</div>
		<div>
			Data przewozu:
			<f:input path="plannedDate" type="date" />
			<f:errors path="plannedDate" cssClass="error" />
		</div>
		<div>
			<input type="submit" value="Dodaj" />
		</div>
	</f:form>
</body>
</html>