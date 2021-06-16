<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Help Center - Archivio</title>
</head>
<body>
	<jsp:include page="header.jsp"/> 
<br/>
	
	<div class="divCenterized">
	
	<legend style="text-align:left;">Elenco dei thread archiviati</legend>
	<hr/>
	<table>
		<tr>
			<th><label class="noNewLine">ID</label></th>
			<th colspan="1"></th>
			<th><label class="noNewLine">Creato</label></th>
			<th><label class="noNewLine">Aggiornato</label></th>
			<th><label class="noNewLine">Chiuso</label></th>
			<th><label class="noNewLine">ID</label></th>
			<th><label class="noNewLine">Nome</label></th>
			<th><label class="noNewLine">Cognome</label></th>
			<th><label class="noNewLine">Domanda</label></th>
		</tr>
	<c:forEach items="${helpCenterList}" var="helpcenter" varStatus="loop">
		<tr>
			<td><label class="noNewLine">${helpcenter.id}</label></td>
			<td>
				<a href="${pageContext.request.contextPath}/helpCenterThread/helpcenterThreadHistory/${helpcenter.id}"><button class="btn btn-sm btn-outline-secondary" type="button">Apri</button></a>
			</td>
			<td><label class="noNewLine"><fmt:formatDate type="both" value="${helpcenter.createdAt}" pattern="E, dd/MM/yyyy, HH:mm:ss" /></label></td>
			<td><label class="noNewLine"><fmt:formatDate type="both" value="${helpcenter.updatedAt}" pattern="E, dd/MM/yyyy, HH:mm:ss" /></label></td>
			<td><label class="noNewLine"><fmt:formatDate type="both" value="${helpcenter.closedAt}" pattern="E, dd/MM/yyyy, HH:mm:ss" /></label></td>
			<td><label class="noNewLine">${helpcenter.fromAccountId}</label></td>
			<td><label class="noNewLine">${helpcenter.account.name}</label></td>
			<td><label class="noNewLine">${helpcenter.account.surname}</label></td>
			<td><label class="noNewLine">${helpcenter.question}</label></td>
		</tr>
	</c:forEach>
	</table>
	</div>
	<hr/>
</body>
</html>