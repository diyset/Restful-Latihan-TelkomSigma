<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>INI USER PAGE</h1>
	<table>
		<thead>
			<th>Id</th>
			<th>Nama User</th>
			<th>Password</th>
			<th>Nama Client</th>
			<th>Address</th>
			<th>CreatedOn</th>
			
		</thead>
		<c:forEach items="${users}" var="x">
			<tbody>
				<td><a href="/edit/${x.id}">${x.id}</a></td>
				<td><a href="/user/${x.id}">${x.id}</a></td>
				<td>${x.username}</td>
				<td>${x.password}</td>
				<td>${x.getClients().getClientName()}</td>
				<td>${x.getClients().getAddress()}</td>
				<td>${x.getClients().getRegisteredOn()}</td>
				
			</tbody>

		</c:forEach>
	</table>
</body>
</html>