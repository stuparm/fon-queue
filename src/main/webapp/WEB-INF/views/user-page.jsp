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
		<div class="col-md-10"></div>
		<div class="col-md-2">
			<div class="ad_user_header">
				<table>
					<tr>
						<td>Korisničko ime: &nbsp;</td>
						<td><b>${userModel.username }</b></td>
					</tr>
					<tr>
						<td>Šalter broj:</td>
						<td><b>${userModel.standNumber }</b></td>
					</tr>
				</table>
				<br />
				<button type="submit" class="btn btn-warning">Odjavi se</button>
			</div>
		</div>
	</div>

	<div class="alert alert-info" role="alert">
		<div class="ad_message">${message }</div>
	</div>

	<div class="ad_margina">
		<div class="col-md-3">
			<div class="ad_dugme_otvori_zatvori">
				<sf:form action="/queue/user" method="POST">
					<input type="hidden" name="action" value="OPEN" />
					<button class="btn btn-success" type="submit">Otvori
						šalter</button>
				</sf:form>
				<br />
				<sf:form action="/queue/user" method="POST">
					<input type="hidden" name="action" value="CLOSE" />
					<button class="btn btn-success" type="submit">Zatvori
						šalter</button>
				</sf:form>
			</div>

			<div class="ad_dugme_omoguci_onemoguci">
				<sf:form action="/queue/user" method="POST">
					<input type="hidden" name="action" value="DISABLE_INSERT" />
					<button class="btn btn-success" type="submit">Zatvori
						prijave studenata</button>
				</sf:form>
				<br />
				<sf:form action="/queue/user" method="POST">
					<input type="hidden" name="action" value="ENABLE_INSERT" />
					<button class="btn btn-success" type="submit">Omogući
						dalje prijave studenata</button>
				</sf:form>
			</div>
		</div>
	</div>

	<div class="col-md-1"></div>

	<div class="col-md-7">
		<div class="ad_main">

			<div class="ad_poruka_status">
				<b> Status šaltera: </b> ${informationModel.status } <br />
			</div>

			<div class="ad_duzina_reda">
				<b> Broj studenata u redu: </b> ${informationModel.queueSize} <br />
				<br /> <br /><br />
			</div>

			<div class="ad_redni_broj">
				<b> Sledeći redni broj: </b>  ${informationModel.nextOrdNumber} <br />
			</div>

			<div class="ad_indeks">
				<b> Broj indeksa studenta: </b>
				<div class="ad_broj"> ${informationModel.nextStudentIndex} 
					<br />
				</div>
			</div>
			<div class="ad_dugme_izbaci">
				<sf:form action="/queue/user" method="POST">
					<input type="hidden" name="action" value="NEXT_STUDENT" />
					<button class="btn btn-primary" type="submit"
						style="font-size: 16px; width: 200px">Izbaci iz reda</button>
				</sf:form>
			</div>
		</div>
	</div>


	<br />
</body>
</html>