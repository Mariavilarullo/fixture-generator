<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 		<script type="text/javascript" src='<c:url value="/js/jquery-1.8.2.js" />'></script>
		<link href="<c:url value="/css/loginStyle.css" />" rel="stylesheet">
	</head>
	<body id="bootstrap-overrides">
	<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang  ? lang : pageContext.request.locale}" scope="session" />
	<fmt:setBundle basename="resources.messages" var="myBundle"/>
	<%@ include file="navbar.jsp" %>
	<h1><fmt:message key="label.welcome" /></h1>
	<div class="container">
	<form:form method="POST" commandName="user" action="userHome.htm">
			<form:label path="username">
				<fmt:message key="label.username" />
			</form:label>
			<form:input  path="username" required="true" />
			<form:errors path="username" cssStyle="color: red" />
			<br />
			<form:label path="password" >
			<fmt:message key="label.password" />
			</form:label>
			<form:input  type="password"  path="password" required="true" />
			<form:errors path="password" cssStyle="color: red" />
			<br/>
			<form:button class="formbutton">Submit</form:button>
		</form:form >
	</div>			
	</body>
</html>