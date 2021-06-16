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

	<button style="margin-left:8px;" class="btn btn-sm btn-outline-secondary" type="button" onclick="showAndHideLongParameters()" id="preview">Mostra/Nascondi Campi</button>
	
	<a href="${pageContext.request.contextPath}/faq/addFaq"><button class="btn btn-sm btn-outline-primary" type="button">Aggiungi FAQ</button></a>
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
	<br/>
	</c:if>
	<c:if test="${faqUpdated!=null}">
	<c:choose>
		<c:when test="${faqUpdated==0}">
			<c:out value="FAQ modificata con successo."/>
			<br/>
		</c:when>
		<c:when test="${faqUpdated==1}">
			<c:out value="Errore nell'update, FAQ non salvata."/>
			<br/>
		</c:when>
	</c:choose>
	<br/>
	</c:if>
	<c:if test="${faqDeleted!=null}">
	<c:choose>
		<c:when test="${faqDeleted==0}">
			<c:out value="FAQ cancellata con successo."/>
			<br/>
		</c:when>
		<c:when test="${faqDeleted==1}">
			<c:out value="Errore nell'eliminazione della FAQ."/>
			<br/>
		</c:when>
	</c:choose>
	<br/>
	</c:if>
	
	<div class="divCenterized">
	
	<legend style="text-align:left;">Elenco FAQs</legend>
	<hr/>
	<table>
		<tr>
			<th><label class="noNewLine">ID</label></th>
			<th colspan="2"><label class="noNewLine">Gestisci FAQ</label></th>
			<th><label class="noNewLine" style="text-align:left;">Domanda</label></th>
			<th><label class="noNewLine" style="text-align:left;">Risposta</label></th>
			
		</tr>
	<c:forEach items="${faqs}" var="faq" varStatus="loop">
		<tr>
			<td><label class="noNewLine">${faq.id}</label></td>
			<td>
				<a href="${pageContext.request.contextPath}/faq/updateFaq/${faq.id}"><button class="btn btn-sm btn-outline-secondary" type="button">Modifica</button></a>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/faq/deleteFaq/${faq.id}"><button class="btn btn-sm btn-outline-danger" type="button">Cancella</button></a>
			</td>
			
			<c:set var="questionVar" value="${faq.question}"></c:set>
			<td><label class="longQ">${faq.question}</label><label class="shortQ"> ${fn:substring(questionVar, 0, 56)}<c:if test="${fn:length(faq.question) gt 56}">...</c:if></label></td>
			
			<c:set var="answerVar" value="${faq.answer}"></c:set>
			<td><label class="longA">${faq.answer}</label><label class="shortA"> ${fn:substring(answerVar, 0, 56)}<c:if test="${fn:length(faq.answer) gt 56}">...</c:if></label></td>
		</tr>
	</c:forEach>
	</table>
	<hr/>
	</div>
	<script>
	$(document).ready(function(){
	    $(".longQ").toggle();
	    $(".longA").toggle();
	    showAndHideLongParameters = function()
	    {
	        $(".shortQ").toggle();
	        $(".shortA").toggle();
	        $(".shortQ").toggleClass("component");
	        $(".shortA").toggleClass("component");
	        $(".longQ").toggle();
	        $(".longA").toggle();
	    }

	});
	</script>
</body>
</html>