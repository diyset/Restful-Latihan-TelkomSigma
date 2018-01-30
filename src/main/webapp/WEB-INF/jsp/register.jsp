<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<h1>INI REGISTER PAGE</h1>

	<form:form action="/save" modelAttribute="formregis"
		method="POST">
		<table>
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input path="username"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password"></form:password></td>
			</tr>
			<tr>
				<td><form:label path="clientName">Nama Client</form:label></td>
				<td><form:input path="clientName"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="address">Alamat</form:label></td>
				<td><form:input path="address"></form:input></td>
			</tr>
			<td><form:button value="submit">Create</form:button></td>
		</table>

	</form:form>
</body>
</html>