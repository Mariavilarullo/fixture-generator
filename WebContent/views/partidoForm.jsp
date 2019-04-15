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
 		<script type="text/javascript" src='<c:url value="/js/jquery-1.8.2.js" />'></script>
 		<link href="<c:url value="/css/style.css" />" rel="stylesheet">
 		<script src="js/main.js"></script>
<title>Cargar partido</title>
</head>
<body>
	<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang  ? lang : pageContext.request.locale}" scope="session" />
	<fmt:setLocale value="${user.lang}" scope="session"/>	
	<div class="bg-1">
			<%@ include file="navbar.jsp" %>
			  <div class="container">
			    <h3 ><fmt:message key="label.form.loaded.teams" /></h3>
			    
			    <div class="row text-center">
			      <c:if test="${empty partidos}">
			       	<fmt:message key="label.form.not.loaded.teams" />
			      </c:if>
			 <div class="row text-center">
		      <c:forEach var="partido" items="${partidos}">
			      <div class="col-sm-4">
			        <div class="thumbnail">
			        <br/>
			          <div class="col-sm-5">
			          	<img src="<c:url value="/img/${partido.equipo1.foto}" />" class="img-circle centered-and-cropped" alt="ASD" width="100" height="100">
			          	<p><strong>${partido.equipo1.nombre}</strong>
			          </div>
			          <div class="col-sm-2">
			          	<p>vs.</p>
			          </div>
			          <div class="col-sm-5">
			          	<img src="<c:url value="/img/${partido.equipo2.foto}" />" class="img-circle centered-and-cropped" alt="ASD" width="100" height="100">
			          	<p><strong>${partido.equipo2.nombre}</strong>
			          </div>
			           <p>${partido.horario}</p>
			           <p><fmt:message key="label.court" /> ${partido.cancha.numero}</p>
			           <p><fmt:message key="label.location" /> ${partido.cancha.sede.nombre}</p>
			          </div>
			      </div>
		      </c:forEach>
		    </div>
		    <form action="<c:url value="/deleteAll/${cancha.id}.htm" />">
		    	<button class="btn"><fmt:message key="label.deleteAll" /></button>
		    </form>
			    </div>
			  </div>
		</div>
	<h1><fmt:message key="label.form.load.new.game" /></h1>
	<div class="container">
	<form:form method="POST" commandName="partido" action="createPartido.htm">
		<br/>
		<form:errors path="orden" cssStyle="color: red" />
		
		<form:label path="cancha">
		<fmt:message key="label.court" /> N° ${cancha.numero}, ${cancha.sede.nombre} ${cancha.tipo}
		</form:label>
		 <form:input name="cancha" value="${cancha.id}" path="cancha.id" readonly="true" hidden="true"></form:input>
        <br/>   
		<form:label path="equipo1">
		<fmt:message key="label.team.1" />:
		</form:label>
		<form:errors path="equipo1" cssStyle="color: red" />
		<form:select id="equipo1" name="equipo1" path="equipo1.id">
			<c:forEach var="equipo" items="${equipos}">
                <option value="${equipo.id}">${equipo.nombre}</option>
            </c:forEach>
        </form:select>
		<br />
		<form:label path="equipo2">
		<fmt:message key="label.team.2" />:
		</form:label>
		<form:select id="equipo2" name="equipo2" path="equipo2.id">
			<c:forEach var="equipo" items="${equipos}">
                <option value="${equipo.id}">${equipo.nombre}</option>
            </c:forEach>
        </form:select>
		<br />
		<form:button id="formbutton"><fmt:message key="label.button.submit" /></form:button>
	</form:form>
	</div>
</body>
</html>