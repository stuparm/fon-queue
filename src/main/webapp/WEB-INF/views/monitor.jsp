<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- pageEncoding="ISO-8859-1"%>  --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<title>Monitor</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="resources/css/styles.css">
	<script src='http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js'></script>
	<script type="text/javascript" src="<c:url value="resources/lib/MonitorApp.js" />" ></script>
</head>
<body ng-app="MonitorApp" class="body_monitor">
	<div ng-controller="MonitorCtrl">
		<div class = "prvi_glavni">
			<div class = "naziv_saltera_prvi">PRVI</div>
			<div class = "naslov">Na redu:</div>
			<div class = "na_redu_broj">{{odgovor.stand_1.next_STUDENT.ord_NUM}}</div>
			<div class = "naslov">Preostalo:</div>
			<div class = "br_preostalih">{{odgovor.stand_1.primary_Q_LENGTH}}</div>
			<div class = "naslov">Pomoćni red:</div>
			<div class = "pomocni">{{pomocni1}} </div>
		</div>
		<div class = "drugi_glavni">
			<div class = "naziv_saltera_drugi">DRUGI</div>
			<div class = "naslov">Na redu:</div>
			<div class = "na_redu_broj">{{odgovor.stand_2.next_STUDENT.ord_NUM}}</div>
			<div class = "naslov">Preostalo:</div>
			<div class = "br_preostalih">{{odgovor.stand_2.primary_Q_LENGTH}}</div>
			<div class = "naslov">Pomoćni red:</div>
			<div class = "pomocni">{{pomocni2}} </div>
		</div>
		<div class = "treci_glavni">
			<div class = "naziv_saltera_treci">TREĆI</div>
			<div class = "naslov">Na redu:</div>
			<div class = "na_redu_broj">{{odgovor.stand_3.next_STUDENT.ord_NUM}}</div>
			<div class = "naslov">Preostalo:</div>
			<div class = "br_preostalih">{{odgovor.stand_3.primary_Q_LENGTH}}</div>
			<div class = "naslov">Pomoćni red:</div>
			<div class = "pomocni">{{pomocni3}} </div>
		</div>
	</div>
	<div class = "pomocni_red">
	</div>
</body>
</html>