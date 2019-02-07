<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Strona logowania</title>
<%-- <link rel="stylesheet" href="<c:url value="/resources/css/bookstore.css"/>"/> --%>
</head>
<body>
 
	<form id="login" method="post">
		<div>
			Login: <input type="text" name="login" />
		</div>
		<div>
			Hasło: <input type="password" name="password" />
		</div>
		<div>
			<input type="submit" value="Zaloguj się" />
		</div>
	</form>
	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/login.js"/>"></script>
</body>
</html>