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
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 		<link href="<c:url value="/css/equipoStyle.css" />" rel="stylesheet">
<title>Equipos</title>
</head>
<body>
	<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang  ? lang : pageContext.request.locale}" scope="session" />
	<div class="bg-1">
			<%@ include file="navbar.jsp" %>
			  <div class="container">
			    <h3 class="text-center"><fmt:message key="label.teams" /></h3>
			    <p class="text-center"><br></p>
			    <div class="row text-center">
			      <c:forEach var="equipo" items="${equipos}">
				      <div class="col-sm-4">
				        <div class="thumbnail">
				        <br/>
				          <form action="verEquipo.htm">
				          <div class="col-sm-12">
				          	<img src="<c:url value="/img/${equipo.foto}" />" class="img-circle centered-and-cropped" alt="${equipo.foto}" width="150" height="150">
				          	<p><strong>${equipo.nombre}</strong>
				          </div>
				          </form>
				        </div>
				      </div>
			      </c:forEach>
			    </div>
			  </div>
		</div>
</body>
</html>