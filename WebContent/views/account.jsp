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
 		<link href="<c:url value="/css/style.css" />" rel="stylesheet">
<title>Admin account</title>
</head>
<body>
	<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang  ? lang : pageContext.request.locale}" scope="session" />
	<div class="bg-1">
			<%@ include file="navbar.jsp" %>
			  <div class="row text-center"">
			    <div class="row text-center">
			    <br/><br/>
			    <div class="profile-userpic">
					<img  src='<c:url value="/img/default.png" />'  alt="defaultprofilepic" width="100" height="100">
				</div>
				<br/>
			    <p><fmt:message key="label.welcome" />, ${user.username}</p>
			    <br/>
			   <a href='<c:url value="/home.htm" />'><i class="glyphicon glyphicon-home"></i>  <fmt:message key="label.all.partidos" /></a>
			    <br/>
			     <br/>
				<a href='<c:url value="/preEquipoForm.htm" />'><i class="glyphicon glyphicon-plus-sign"></i>  <fmt:message key="label.new.equipo" /></a>
			     <br/>
			     <br/>
			     <a href='<c:url value="/prePartidoForm.htm" />'><i class="glyphicon glyphicon-plus-sign"></i>  <fmt:message key="label.new.team" /></a>
			    <br/>
				 <br/>
				<a href='<c:url value="/equiposUsuario.htm" />'><i class="glyphicon glyphicon-folder-open"></i>  <fmt:message key="label.user.equipos" /></a>
				<br/>
				 <br/>
				</h1>
				 </div>
			  </div>
		</div>
</body>
</html>