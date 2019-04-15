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
	<div class="container"><form:form method="POST" commandName="equipo" action="createEquipo.htm" enctype="multipart/form-data">
		<br />
		<form:label path="tipo">
		<fmt:message key="label.type"/>
		<c:if test="${ equipo.tipo=='UNI'}">
		<fmt:message key="label.professional"/>
		</c:if>
		<c:if test="${ equipo.tipo=='AMA'}">
		Amateur
		</c:if>
		</form:label>
		 <form:input name="tipo" value="${equipo.tipo}" path="tipo" readonly="true" hidden="true"></form:input>
        <br/>   
		<form:label path="nombre">
		<fmt:message key="label.name" />
		</form:label>
		<form:input path="nombre"/>
		<form:errors path="nombre" cssStyle="color: red" />
		<br />
		<br />
		<form:errors path="foto" cssStyle="color: red" />
		<fmt:message key="label.foto" />: <input type="file" name="pic" id="file" class="inputfile" />
		<label for="file"><fmt:message key="label.load.pic" /></label>
		<br />
		<form:label path="historia">
		<fmt:message key="label.history" />:
		</form:label>
		<form:input path="historia" />
		<form:errors path="historia" cssStyle="color: red" />
		<br />
		<c:if test="${equipo.tipo=='UNI'}">
			<form:label path="dt">
			<fmt:message key="label.coach" />
			</form:label>
			<form:errors path="dt" cssStyle="color: red" />
			<form:input path="dt" />
			<br />
			</c:if>
			<c:if test="${equipo.tipo=='AMA'}">
		<form:label path="categoria">
		<fmt:message key="label.category" />
		</form:label>
		<form:errors path="categoria" cssStyle="color: red" />
		<br/>
		<select id="categoria" name="categoria" path="categoria">
				<option disabled selected value> -- <fmt:message key="label.choose.category" /> -- </option>	
                <option value="Ocasional">Ocasional</option>
                <option value="Regular">Regular</option>
                <option value="Viene siempre"><fmt:message key="label.always" /></option>
                <option value="No se va nunca"><fmt:message key="label.never" /></option>
        </select>
        </c:if>
        <form:button id="formbutton"><fmt:message key="label.button.submit" /></form:button>
	</form:form>
	</div>
</body>
</html>