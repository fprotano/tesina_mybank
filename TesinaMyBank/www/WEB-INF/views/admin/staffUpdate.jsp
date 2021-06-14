<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrazione</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<br/>

<form:form method="post" modelAttribute="staffToUpdate" action="${pageContext.request.contextPath}/staff/staffUpdate">

<h4>Modifica dati del personale dello Staff</h4>
<div style="margin:8px;";>
	<label>ID: ${staffToUpdate.id}</label>
	<form:input hidden="hidden" path="id" />
	<br/>
	<label>Nome</label>
	<br/>
	<form:input path="name" />
	<br/>
	<label>Cognome</label>
	<br/>
	<form:input path="surname" />
	<br/>
	<label>Email: </label>
	<br/>
	<form:input path="email" />
	<form:input hidden="hidden" path="password" />
	<table>
	<thead></thead>
		<tr>
			<td><form:radiobutton path="roleId" value="1"/></td>
			<td><c:out value="Amministratore"/></td>
	    </tr>
	    <tr>
			<td><form:radiobutton path="roleId" value="2"/></td>
			<td><c:out value="Validatore"/></td>
	    </tr>
	    <tr>
			<td><form:radiobutton path="roleId" value="3"/></td>
			<td><c:out value="Help Center"/></td>
	    </tr>
	</table>
	<hr/>
	<input type="submit" value="Modifica">
	</form:form>
</div>

</body>
</html>