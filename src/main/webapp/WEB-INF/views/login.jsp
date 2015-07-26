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
<title>Studentska služba FON-a</title>

<link type="text/css" rel="stylesheet" href="resources/css/styles.css" />
<link type="text/css" rel="stylesheet"
	href="resources/css/bootstrap.css" />

</head>
<body>


	<div class="page-header">
		<div class="ad_header">
			<h2>Fakultet organizacionih nauka</h2>
			<h3>Servis studentske službe</h3>	
		</div>
	</div>



	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">
			<div class="ad_login-form-error">
				<div class="col-md-1"></div>
				<div class="col-md-11">
					<c:if test="${not empty errorMessage}">
						<div>${ errorMessage }</div>
					</c:if>
				</div>
			</div>
		</div>
	</c:if>

	<div class="ad_login-form">
		<div class="col-md-1"></div>
		<div class="col-md-11">
			<form method="post" action="j_spring_security_check">
				<label for="j_username">Korisničko ime</label> <br /> <input
					type="text" name="j_username"> <br /> <br /> <label
					for="j_password">Šifra</label> <br /> <input type="password"
					name="j_password"> <br /> <br />
				<button type="submit" class="btn btn-primary">Prijavi se</button>
			</form>
		</div>
	</div>

</body>
</html>