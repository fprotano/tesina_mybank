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
	
	<legend>Elenco dei thread aperti:</legend>
	<hr/>
	<table>
		<tr>
			<th><label style="width: 28px;">ID</label></th>
			<th colspan="2"></th>
			<th><label>Creato</label></th>
			<th><label>Aggiornato</label></th>
			<th><label>Chiuso</label></th>
			<th><label>ID</label></th>
		</tr>
	<c:forEach items="${helpcenterList}" var="helpcenter" varStatus="loop">
		<tr>
			<td>${helpcenter.id}</td>
			
			<td>
				<a href="${pageContext.request.contextPath}/staff/staffUpdate/${staff.id}"><button class="btn btn-sm btn-outline-secondary" type="button">Apri</button></a>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/staff/staffUpdate/${staff.id}"><button class="btn btn-sm btn-outline-danger" type="button">Archivia</button></a>
			</td>
			
			<td>${helpcenter.createdAt}</td>
			<td>${helpcenter.updatedAt}</td>
			<td>${helpcenter.closedAt}</td>
			<td>${helpcenter.fromAccountId}</td>
			
		</tr>
	</c:forEach>
	</table>
	<hr/>
	
	
</body>
</html>