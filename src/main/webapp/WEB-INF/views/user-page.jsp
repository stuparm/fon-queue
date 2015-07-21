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
			Fakultet organizacionih nauka <small>Studentska sluzba</small>
		</h1>
	</div>


	<div class="alert alert-info" role="alert">${message }</div>


	<div class=col-sm-10></div>
	<div class="col-sm-2">
		<table>
			<tr>
				<td>Korisnicko ime:   </td>
				<td>${userModel.username }</td>
			</tr>
			<tr>
				<td>Salter broj:   </td>
				<td>${userModel.standNumber }</td>
			</tr>
		</table>

	</div>

	<br />
	<br />

	<sf:form action="/queue/user" method="POST">
		<input type="hidden" name="action" value="OPEN" />
		<button type="submit">Otvori salter</button>
	</sf:form>

	<br />

	<sf:form action="/queue/user" method="POST">
		<input type="hidden" name="action" value="CLOSE" />
		<button type="submit">Zatvori salter</button>
	</sf:form>

	<br />
	<br /> Status: ${informationModel.status }
	<br /> Sledeci redni broj: ${informationModel.nextOrdNumber}
	<br /> Student index: ${informationModel.nextStudentIndex}
	<br /> Duzina reda: ${informationModel.queueSize}
	<br />

	<br />

	<sf:form action="/queue/user" method="POST">
		<input type="hidden" name="action" value="NEXT_STUDENT" />
		<button type="submit">Izbaci iz reda</button>
	</sf:form>

	<br />

	<sf:form action="/queue/user" method="POST">
		<input type="hidden" name="action" value="DISABLE_INSERT" />
		<button type="submit">Онемогући даљи долазак</button>
	</sf:form>

	<sf:form action="/queue/user" method="POST">
		<input type="hidden" name="action" value="ENABLE_INSERT" />
		<button type="submit">Омогући убацивање и даље</button>
	</sf:form>






</body>
</html>