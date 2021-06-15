<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Help Center</title>
</head>
<body>
	<jsp:include page="header.jsp"/> 
<br/>
	
	<legend>Elenco dei thread sull'argomento:</legend>
	<hr/>
	<table>
		<tr>
			<th><label style="width: 28px;">ID</label></th>
			<th><label>Data</label></th>
			<th><label>Domanda</label></th>
			<th><label>Risposta</label></th>
		</tr>
	<c:forEach items="${helpCenterThreadList}" var="helpcenterThread" varStatus="loop">
		<tr>
			<td>${helpcenterThread.id}</td>
			<td>${helpcenterThread.createdAt}</td>
			<td>${helpcenterThread.question}</td>
			<td>${helpcenterThread.answer}</td>
		</tr>
	</c:forEach>
	</table>
	<hr/>
	
</body>
</html>