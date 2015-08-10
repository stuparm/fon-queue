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

	<div class="page-header">
		<div class="ad_header">
			<h4>Prijava za šaltere studentske službe</h4>
			<div style="margin-top: -20px">
				<a class="btn btn-warning" href="<c:url value="login" />">Log in</a>
				<a class="btn btn-warning" style="margin-left: 15px;" href="<c:url value="/" />">Osveži</a>
			</div>
		</div>
	</div>

	<div class="alert alert-info" role="alert" style="height: 60px; margin-bottom: 50px;">
		<div class="ad_message">${message }</div>
	</div>

	<!-- SALTERI -->

	<div class="col-md-2"></div>

	<!-- SALTER 1 -->

	<div class="ad_salter_1" style="text-align: center;">
		<div class="col-md-2"
			style="-webkit-box-shadow: 3px 3px 29px -4px rgba(212, 201, 212, 0.85); -moz-box-shadow: 3px 3px 29px -4px rgba(212, 201, 212, 0.85); box-shadow: 3px 3px 29px -4px rgba(212, 201, 212, 0.85);">
			<h4>
				<b>Šalter 1</b>
			</h4>
			<p>- &nbsp; ${stand_1.open } &nbsp; -</p>
			<br />
			<p style="font-size: 20px;">
				<img alt="" src="resources/img/user.png"
					style="width: 50px; height: auto;"> <br />
				${stand_1.nextIndex }<br /> Redni broj: ${stand_1.ordNum }
			</p>
			<br />
			<p style="font-size: 16px;">Dužina reda: ${stand_1.length }</p>
			<p style="font-size: 16px;">Vaš redni broj: ${ordNum_1 }</p>

		</div>
	</div>

	<div class="col-md-1"></div>

	<!-- SALTER 2 -->
	<div class="ad_salter_2" style="text-align: center;">
		<div class="col-md-2"
			style="-webkit-box-shadow: 3px 3px 29px -4px rgba(212, 201, 212, 0.85); -moz-box-shadow: 3px 3px 29px -4px rgba(212, 201, 212, 0.85); box-shadow: 3px 3px 29px -4px rgba(212, 201, 212, 0.85);">
			<h4>
				<b>Šalter 2</b>
			</h4>
			<p>- &nbsp; ${stand_2.open } &nbsp; -</p>
			<br />
			<p style="font-size: 20px;">
				<img alt="" src="resources/img/user.png"
					style="width: 50px; height: auto;"> <br />
				${stand_2.nextIndex }<br /> Redni broj: ${stand_2.ordNum }
			</p>
			<br />
			<p style="font-size: 16px;">Dužina reda: ${stand_2.length }</p>
			<p style="font-size: 16px;">Vaš redni broj: ${ordNum_2 }</p>
		</div>
	</div>

	<div class="col-md-1"></div>

	<!-- SALTER 3 -->
	<div class="ad_salter_3" style="text-align: center;">
		<div class="col-md-2"
			style="-webkit-box-shadow: 3px 3px 29px -4px rgba(212, 201, 212, 0.85); -moz-box-shadow: 3px 3px 29px -4px rgba(212, 201, 212, 0.85); box-shadow: 3px 3px 29px -4px rgba(212, 201, 212, 0.85);">
			<h4>
				<b>Šalter 3</b>
			</h4>
			<p>- &nbsp; ${stand_3.open } &nbsp; -</p>
			<br />
			<p style="font-size: 20px;">
				<img alt="" src="resources/img/user.png"
					style="width: 50px; height: auto;"> <br />
				${stand_3.nextIndex }<br /> Redni broj: ${stand_3.ordNum }
			</p>
			<br />
			<p style="font-size: 16px;">Dužina reda: ${stand_3.length }</p>
			<p style="font-size: 16px;">Vaš redni broj: ${ordNum_3 }</p>
		</div>
	</div>	
	<br />
	<br />
	<br />
	<br />
	<div class="ad_prijava">
		<div class="col-md-12" style="margin-left: 110px;">
			<sf:form class="form-horizontal" modelAttribute="studentModel"
				action="/queue/" method="post" style="margin-left:-100px;">

				<div class="form-group">
					<sf:label path="indexNumber" class="col-sm-5 control-label">Broj indeksa:</sf:label>
					<div class="col-sm-2">
						<sf:input path="indexNumber" class="form-control"
							placeholder="Primer: 2010/0048" />
						<sf:errors path="indexNumber" cssClass="ms_error" />
					</div>
				</div>

				<div class="form-group">
					<sf:label path="standNumber" class="col-sm-5 control-label">Broj šaltera:</sf:label>
					<div class="col-sm-2">
						<sf:input path="standNumber" class="form-control"
							placeholder="Za proveru, ostavi prazno" />
						<sf:errors path="standNumber" cssClass="ms_error" />
					</div>
				</div>

				<div class="ms_antispam form-group">
					<sf:label path="antiSpam" class="col-sm-5 control-label">Ostavi ovo polje prazno:</sf:label>
					<div class="col-sm-2">
						<sf:input path="antiSpam" class="form-control" />
						<sf:errors path="antiSpam" cssClass="ms_error" />
					</div>
				</div>


				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-2">
						<button type="submit" class="btn btn-primary"
							style="width: 140px;">Prijavi se / Proveri</button>
					</div>
				</div>
			</sf:form>
		</div>
	</div>
	<br />
	<br />
</body>
</html>