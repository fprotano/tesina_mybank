<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Help Center</title>
</head>
<body>
	<jsp:include page="header.jsp"/> 
<br/>
<c:if test="${threadArchived!=null}">
	<c:choose>
		<c:when test="${threadArchived==0}">
			<c:out value="Thread archiviato con successo."/>
			<br/>
		</c:when>
		<c:when test="${threadArchived==1}">
			<c:out value="Errore nell'inserimento del thread."/>
			<br/>
		</c:when>
	</c:choose>
</c:if>
	<div class="divCenterized">
	
	<legend style="text-align:left;">Elenco dei thread aperti:</legend>
	<hr/>
	<table>
		<tr>
			<th><label class="noNewLine">ID</label></th>
			<th colspan="2"><label class="noNewLine"></label></th>
			<th><label class="noNewLine">Creato</label></th>
			<th><label class="noNewLine">Aggiornato</label></th>
			<th><label class="noNewLine">ID</label></th>
			<th><label class="noNewLine">Nome</label></th>
			<th><label class="noNewLine">Cognome</label></th>
			<th><label class="noNewLine">Domanda</label></th>
		</tr>
	<c:forEach items="${helpCenterList}" var="helpcenter" varStatus="loop">
		<tr>
			<td>${helpcenter.id}</td>
			<td>
				<a href="${pageContext.request.contextPath}/helpCenterThread/helpcenterThreadList/${helpcenter.id}"><button class="btn btn-sm btn-outline-secondary" type="button">Apri</button></a>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/helpCenter/helpCenterThreadArchiveThread/${helpcenter.id}"><button class="btn btn-sm btn-outline-danger" type="button">Archivia</button></a>
			</td>
			<td><label class="noNewLine"><fmt:formatDate type="both" value="${helpcenter.createdAt}" pattern="E, dd/MM/yyyy, HH:mm:ss" /></label></td>
			<td><label class="noNewLine"><fmt:formatDate type="both" value="${helpcenter.updatedAt}" pattern="E, dd/MM/yyyy, HH:mm:ss" /></label></td>
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