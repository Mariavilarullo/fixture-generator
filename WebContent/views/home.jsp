<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
 		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
 		<link href="<c:url value="/css/style.css" />" rel="stylesheet">
		<title>Home</title>
	</head>
	<body>
		<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang  ? lang : pageContext.request.locale}" scope="session" />
		<fmt:setLocale value="${user.lang}" scope="session"/>	
		<div class="bg-1">
		<%@ include file="navbar.jsp" %>
		  <div class="container">
		    <h3 class="text-center" id="fixture">FIXTURE 2018</h3>
		      <form:form method="POST" commandName="searchForm" action="search.htm">
		        <div class="input-group">
		        	<form:select id="id" name="id" path="id" >
		        			<option disabled selected value> <fmt:message key="label.filter.sede" /> </option>
						<c:forEach var="sede" items="${sedes}">
			                <option value="${sede.id}"> ${sede.nombre}</option>
			        	</c:forEach>
			        </form:select>
		            <input type="text" class="form-control" path="contenido" name="contenido" >
		            <div class="input-group-btn">
		                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
		            </div>
		        </div>
        	</form:form>
        	<div id="test">
        	
        	</div>
		    <p class="text-center"><fmt:message key="label.matches" /><br></p>
		    <div class="row text-center">
		      <c:forEach var="partido" items="${partidos}">
		      	<form action="<c:url value="/deletePartido/${partido.id}.htm" />">
			      <div class="col-sm-4">
			        <div class="thumbnail">
			        <br/>
			       	<c:if test="${(partido.equipo1.tipo =='UNI') || (partido.equipo2.tipo =='UNI')}">
			          		<i class="material-icons md-54">star</i>
			        </c:if>
			        <c:if test="${(partido.equipo1.tipo =='AMA') || (partido.equipo2.tipo =='AMA')}">
			          <br/>
			         </c:if>
			        <br/>
			          <div class="col-sm-5">
			          	<img src="<c:url value="/img/${partido.equipo1.foto}" />" class="img-circle centered-and-cropped" alt="ASD" width="100" height="100">
			          	<p><strong><a href="<c:url value="/ver/${partido.equipo1.id}.htm" />" >${partido.equipo1.nombre}</a></strong>
			          </div>
			          <div class="col-sm-2">
			          	<br/>
			          	<p>vs.</p>
			          </div>
			          <div class="col-sm-5">
			          	<img src="<c:url value="/img/${partido.equipo2.foto}" />" class="img-circle centered-and-cropped" alt="ASD" width="100" height="100">
			          	<p><strong><a href="<c:url value="/ver/${partido.equipo2.id}.htm" />" >${partido.equipo2.nombre}</a></strong>
			          </div>
			           <p> ${partido.horario} </p>
			           <p><fmt:message key="label.court" /> ${partido.cancha.numero}</p>
			           <p><fmt:message key="label.location" /> ${partido.cancha.sede.nombre}</p>
			          <c:if test="${not empty user.username}">
			          		<button class="btn"><fmt:message key="label.delete" /></button>
			          </c:if>
			        </div>
			      </div>
			      </form>
		      </c:forEach>
		    </div>
		  </div>
		</div>
	</body>
</html>