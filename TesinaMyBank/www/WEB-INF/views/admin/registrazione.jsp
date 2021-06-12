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



<form:form method="post" modelAttribute="staffRegistrato" action="${pageContext.request.contextPath}/staff/registrazione">

<h4>Registra un nuovo membro dello Staff</h4>
<div style="margin:8px;";>
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
	<br/>
	<label>Password: </label>
	<br/>
	<form:input path="password" />
	<hr/>
	<form:select path="roleId">
	<option value='1'> Amministratore </option>
	<option value='2'>Verificatore</option>
	<option value='3'>Help Desk</option>
	</form:select>
	<input type="submit" value="Registra">
	</form:form>
</div>

</body>
</html>