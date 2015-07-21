<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- pageEncoding="ISO-8859-1"%>  --%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link type="text/css" rel="stylesheet" href="resources/css/styles.css" />
<link type="text/css" rel="stylesheet"
	href="resources/css/bootstrap.min.css" />

</head>
<body>


	<div class="page-header">
		<h1>
			Fakultet organizacionih nauka  <small>Studentska sluzba</small>
		</h1>
	</div>



	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${ errorMessage }</div>
	</c:if>


	<form method="post" action="j_spring_security_check">

		<label for="j_username">Username</label> <br /> <input type="text"
			name="j_username"> <br /> <br /> <label for="j_password">Password</label>
		<br /> <input type="password" name="j_password"> <br /> <br />

		<button type="submit">Log in</button>
	</form>

</body>
</html>