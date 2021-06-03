<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
</head>
<body>
 	<jsp:include page="header.jsp"/> 
	
<fieldset>
<b>Nome: ${staff.name}</b>
<br>
<b>Cognome: ${staff.surname}</b>
<br>
<b>Email: ${staff.email}</b>
<br>
<b>Password: ${staff.password}</b>
<br>
<b>RoleId: ${staff.roleId}</b>
</fieldset>
<br>
<br>
<br>
${messaggio}
</body>
</html>