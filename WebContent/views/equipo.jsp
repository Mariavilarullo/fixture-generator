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
<title>Equipos</title>
</head>
<body>
	<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang  ? lang : pageContext.request.locale}" scope="session" />
	<div class="bg-1">
			<%@ include file="navbar.jsp" %>
			  <div class="container">
			    <div class="row text-center">
				<img src="<c:url value="/img/${equipo.foto}" />" class="img-circle centered-and-cropped" alt="${equipo.foto}" width="270" height="270">
				   <br/>
				   <br/>
				   <br/>
				   <fmt:message key="label.name"/>
				   <br/>
				   <p><strong>${equipo.nombre}</strong></p>
				   <br/>
				   <c:if test="${not empty equipo.historia}">
				   <fmt:message key="label.history"/> 
				   <br/>
				   <p>${equipo.historia}</p>
				   </c:if>
				   <br/>
				   <c:if test="${equipo.tipo =='UNI'}">
				   	<fmt:message key="label.coach"/>
				   <br/>
				   <p>${equipo.dt}</p>
				   </c:if>
				   <br/>
				   <c:if test="${equipo.tipo =='AMA'}">
				   	<fmt:message key="label.category"/>
				    <br/>
				   <p>${equipo.categoria}</p>
				   </c:if>
			    </div>
			  </div>
		</div>
		<div class="row text-center">
			<h3><fmt:message key="label.part.games" /></h3>
		      <c:forEach var="partido" items="${partidos}">
			      <div class="col-sm-4">
			        <div class="thumbnail">
			        <br/>
			          <div class="col-sm-5">
			          	<img src="<c:url value="/img/${partido.equipo1.foto}" />" class="img-circle centered-and-cropped" alt="ASD" width="100" height="100">
			          	<p><strong><a href="<c:url value="/ver/${partido.equipo1.id}.htm" />" >${partido.equipo1.nombre}</a></strong>
			          </div>
			          <div class="col-sm-2">
			          	<p>vs.</p>
			          </div>
			          <div class="col-sm-5">
			          	<img src="<c:url value="/img/${partido.equipo2.foto}" />" class="img-circle centered-and-cropped" alt="ASD" width="100" height="100">
			          	<p><strong><a href="<c:url value="/ver/${partido.equipo2.id}.htm" />" >${partido.equipo2.nombre}</a></strong>
			          </div>
			           <p> ${partido.horario} </p>
			           <p><fmt:message key="label.court" /> ${partido.cancha.numero}</p>
			           <p><fmt:message key="label.location" /> ${partido.cancha.sede.nombre}</p>
			        </div>
			      </div>
		      </c:forEach>
		    </div>
</body>
</html>