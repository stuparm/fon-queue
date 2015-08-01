<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- pageEncoding="ISO-8859-1"%>  --%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Studentska služba FON-a</title>

<link type="text/css" rel="stylesheet" href="resources/css/styles.css" />
<link type="text/css" rel="stylesheet"
	href="resources/css/bootstrap.css" />


</head>
<body>
	<div class="alert alert-info" role="alert">
		<div class="ad_message">${message }</div>
	</div>
	
	<br/>
	<br/>
	
	<!-- SALTER 1 -->
	<div>
		${stand_1.open }             <br/>
		${stand_1.nextIndex }        <br/>
		${stand_1.ordNum }           <br/>
		${stand_1.length }           <br/>
	</div>

	<br/>
	<br/>

	<!-- SALTER 2 -->
	<div>
		${stand_2.open }             <br/>
		${stand_2.nextIndex }        <br/>
		${stand_2.ordNum }           <br/>
		${stand_2.length }           <br/>
	</div>

	<br/>
	<br/>

	<!-- SALTER 3 -->
	<div>
		${stand_3.open }             <br/>
		${stand_3.nextIndex }        <br/>
		${stand_3.ordNum }           <br/>
		${stand_3.length }           <br/>
	</div>

	<br/>
	<br/>

	<sf:form class="form-horizontal" modelAttribute="studentModel"
		action="/queue/" method="post">

		<div class="form-group">
			<sf:label path="indexNumber" class="col-sm-5 control-label">Broj indeksa:</sf:label>
			<div class="col-sm-4">
				<sf:input path="indexNumber" class="form-control"
					placeholder="2010/0048" />
				<sf:errors path="indexNumber" cssClass="ms_error" />
			</div>
		</div>

		<div class="form-group">
			<sf:label path="standNumber" class="col-sm-5 control-label">Broj šaltera:</sf:label>
			<div class="col-sm-4">
				<sf:input path="standNumber" class="form-control" placeholder="Ukoliko je provera, ostavi prazno" />
				<sf:errors path="standNumber" cssClass="ms_error" />
			</div>
		</div>

		 
		<div class="ms_antispam form-group">
			<sf:label path="antiSpam" class="col-sm-5 control-label">Ostavi ovo polje prazno:</sf:label>
			<div class="col-sm-4">
				<sf:input path="antiSpam" class="form-control" />
				<sf:errors path="antiSpam" cssClass="ms_error" />
			</div>
		</div>

		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-2">
				<button type="submit" class="btn btn-default">Prijavi se / Proveri</button>
			</div>
		</div>


	</sf:form>

	<br/>
	<br/>

	
	Stanje:<br/>
	
	Salter_1 = ${ordNum_1 } <br/>
	Salter_2 = ${ordNum_2 } <br/>
	Salter_3 = ${ordNum_3 } <br/>
	
	
	<br/>
	<br/>
	
	<a class="btn btn-warning"
					href="<c:url value="login" />">Login</a>
	
	<a class="btn btn-warning"
					href="<c:url value="/" />">Osveži</a>


</body>
</html>