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
<title>My test</title>
</head>
<body>
	<h1>
		<fmt:message key="title.locale.switch" />
	</h1>

	<fmt:setBundle basename="resources.messages2" var="myBundle" />
	<h2>
		<fmt:message key="key1" bundle="${myBundle}" />
	</h2>

	<c:url value="/localeChange.htm" var="myAction" />
	<form:form action="${myAction}" commandName="language">
		<form:input path="lang" /> <form:errors path="lang" cssStyle="color: red"/> 
	</form:form>
</body>
</html>