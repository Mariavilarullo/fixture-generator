<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<c:url value="/css/style.css" />" rel="stylesheet">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 		<!-- <script src="/js/asdasd.js"></script>--->
<title>Cargar equipo</title>
</head>
<body>
	<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang  ? lang : pageContext.request.locale}" scope="session" />

	<%@ include file="navbar.jsp" %>
	<h1><fmt:message key="label.new.equipo" /></h1>
	<div class="container"><form:form method="POST" commandName="equipo" action="equipoForm.htm">
		<br />
		<form:label path="tipo">
		<fmt:message key="label.type" />:
		</form:label>
		<br/>
		<select id="tipo" name="tipo">
                <option value="AMA">Amateur</option>
                <option value="UNI"><fmt:message key="label.professional" /></option>
        </select>
        <br/>
		 <form:button id="formbutton"><fmt:message key="label.button.submit" /></form:button>
	</form:form>
	</div>
</body>
</html>