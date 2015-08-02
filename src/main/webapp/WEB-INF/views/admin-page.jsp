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

<script type="text/javascript">
	function callReset() {
		if (confirm("Da li ste sigurni da želite da resetujete stanje svih šaltera ?") == true) {
			xmlhttp = new XMLHttpRequest();
			xmlhttp.open("POST", "/queue/admin", true);
			xmlhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xmlhttp.send("action=RESET");
		}
	}
</script>
</head>
<body>
	<div class="page-header">
		<div class="col-md-9"></div>
		<div class="col-md-3">
			<div class="ad_user_header">
				<table>
					<tr>
						<td>Korisničko ime: &nbsp;</td>
						<td><b>${adminModel.username }</b></td>
					</tr>
					<tr>
						<td>Ime i prezime:</td>
						<td><b>${adminModel.firstName } &nbsp;
								${adminModel.lastName } </b></td>
					</tr>
				</table>
				<br /> <a class="btn btn-warning"
					href="<c:url value="j_spring_security_logout" />">Odjavi se</a>
				<!-- <button type="submit" class="btn btn-warning">Odjavi se</button> -->
			</div>
		</div>
	</div>

	<!-- STATUSNA LINIJA -->
	<div class="alert alert-info" role="alert">
		<div class="ad_message">${message }</div>
	</div>

	<!-- EKRAN -->
	<div class="col-md-12">

		<!-- CENTAR -->
		<div class="col-md-8">

			<!-- PRVI RED -->
			<div class="col-md-12">

				<!-- TABELA KORISNICI -->
				<div class="col-md-4">

					<h3>Korisnici:</h3>
					<table class="table table-bordered">
						<tr>
							<th>Korisničko ime</th>
							<th>Broj šaltera</th>
						</tr>
						<c:forEach var="user" items="${users }">
							<tr>
								<td><c:out value="${user.username }"></c:out></td>
								<td><c:out value="${user.standNumber }"></c:out></td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<!-- PRAZNINA -->
				<div class="col-md-1"></div>

				<!-- TABELA BAZA -->
				<div class="col-md-7">

					<h3>Konekcija sa bazom:</h3>
					<table class="table table-bordered">
						<tr>
							<th>Naziv</th>
							<th>Vrednost</th>
						</tr>
						<c:forEach var="prop" items="${props }">
							<tr>
								<td><c:out value="${prop.key }"></c:out></td>
								<td><c:out value="${prop.value }"></c:out></td>
							</tr>
						</c:forEach>
					</table>
					
					<div>
					<button class="btn btn-warning" onclick="callReset()">Resetuj šaltere</button>
				</div>
				</div>
				
			</div>


			<!-- DRUGI RED -->
			<div class="col-md-12">

				<!-- PRAZNINA 
				<div class="col-md-1"></div> -->

				<!-- TABELA ADMINI -->
				<div class="col-md-11">

					<h3>Administratori:</h3>
					<table class="table table-bordered">
						<tr>
							<th>Korisničko ime</th>
							<th>Ime</th>
							<th>Prezime</th>
							<th>E-mail</th>
							<th>Telefon</th>
						</tr>
						<c:forEach var="admin" items="${admins }">
							<tr>
								<td><c:out value="${admin.username }"></c:out></td>
								<td><c:out value="${admin.firstName }"></c:out></td>
								<td><c:out value="${admin.lastName }"></c:out></td>
								<td><c:out value="${admin.email }"></c:out></td>
								<td><c:out value="${admin.telephone }"></c:out></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

		<!-- DESNO -->
		<div class="col-md-4">
			<sf:form class="form-horizontal" modelAttribute="crudModel"
				action="/queue/admin" method="post">

				<div class="form-group">
					<sf:label path="username" class="col-sm-3 control-label">Korisnik*:</sf:label>
					<div class="col-sm-8">
						<sf:input path="username" class="form-control"
							placeholder="Korisničko ime" />
						<sf:errors path="username" cssClass="ms_error" />
					</div>
				</div>

				<div class="form-group">
					<sf:label path="password" class="col-sm-3 control-label">Lozinka*:</sf:label>
					<div class="col-sm-8">
						<sf:password path="password" class="form-control" />
						<sf:errors path="password" cssClass="ms_error" />
					</div>
				</div>

				<div class="form-group">
					<sf:label path="standNumber" class="col-sm-3 control-label">Šalter:</sf:label>
					<div class="col-sm-2">
						<sf:input path="standNumber" class="form-control" />
						<sf:errors path="standNumber" cssClass="ms_error" />
					</div>
					<button type="submit" class="btn btn-primary col-sm-4"
						name="action" value="CRUD_INSERT_USER">Unesi korisnika</button>
				</div>

				<div class="form-group">
					<sf:label path="firstName" class="col-sm-3 control-label">Ime:</sf:label>
					<div class="col-sm-8">
						<sf:input path="firstName" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<sf:label path="lastName" class="col-sm-3 control-label">Prezime:</sf:label>
					<div class="col-sm-8">
						<sf:input path="lastName" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<sf:label path="email" class="col-sm-3 control-label">E-mail:</sf:label>
					<div class="col-sm-8">
						<sf:input path="email" class="form-control"
							placeholder="primer@mail.com" />
					</div>
				</div>

				<div class="form-group">
					<sf:label path="telephone" class="col-sm-3 control-label">Telefon:</sf:label>
					<div class="col-sm-4">
						<sf:input path="telephone" class="form-control"
							placeholder="0631234567" />
					</div>
					<button type="submit" class="btn btn-primary col-sm-3"
						name="action" value="CRUD_INSERT_ADMIN">Unesi admina</button>
				</div>

				<hr>
				<div class="form-group">
					<div class="col-sm-3"></div>
					<button type="submit" class="btn btn-primary col-sm-3"
						style="margin-left: 15px;" name="action" value="CRUD_UPDATE">Izmeni</button>
					<button type="submit" class="btn btn-primary col-sm-3"
						name="action" value="CRUD_DELETE">Obriši</button>
				</div>
			</sf:form>
		</div>
	</div>
</body>
</html>