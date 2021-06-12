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
<title>Lista FAQs</title>
</head>
<body>

<jsp:include page="header.jsp"/> 
<br/>
	<a href="${pageContext.request.contextPath}/staff/addFaq"><button class="btn btn-sm btn-outline-primary" type="button">Aggiungi FAQ</button></a>
	<br/>
	<c:if test="${faqAdded!=null}">
	<c:choose>
		<c:when test="${faqAdded==0}">
			<c:out value="Nuova FAQ inserita."/>
			<br/>
		</c:when>
		<c:when test="${faqAdded==1}">
			<c:out value="Errore nell'inserimento, FAQ non inserita."/>
			<br/>
		</c:when>
	</c:choose>
	</c:if>
	<br/>
	<legend>Elenco FAQs</legend>
	<hr/>
	<table>
		<tr>
			<th>ID</th>
			<th>Domanda</th>
			<th>Risposta</th>
			
		</tr>
	<c:forEach items="${faqs}" var="faq" varStatus="loop">
		<tr>
			<td>${faq.id}</td>
			<td>${faq.question}</td>
			<td>${faq.answer}</td>
		</tr>
	</c:forEach>
	</table>
	<hr/>
</body>
</html>