<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elenco Staff</title>
</head>
<body>

<jsp:include page="header.jsp"/> 
<br/>
	
	<legend>Elenco del personale dello staff</legend>
	<hr/>
	<table>
		<tr>
			<th><label style="width: 28px;">ID</label></th>
			<th></th>
			<th><label>Nome</label></th>
			<th><label>Cognome</label></th>
			<th><label>Email</label></th>
			<th><label>Ruolo</label></th>
		</tr>
	<c:forEach items="${staffList}" var="staff" varStatus="loop">
		<tr>
			<td>${staff.id}</td>
			
			<td>
				<a href="${pageContext.request.contextPath}/staff/staffUpdate/${staff.id}"><button class="btn btn-sm btn-outline-warning" type="button">Modifica</button></a>
			</td>
			
			<td>${staff.name}</td>
			<td>${staff.surname}</td>
			<td>${staff.email}</td>
			<td>${staff.role.title}</td>
		</tr>
	</c:forEach>
	</table>
	<hr/>
	
</body>
</html>