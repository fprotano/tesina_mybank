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

	<button class="btn btn-sm btn-outline-secondary" type="button" onclick="showAndHideLongParameters()" id="preview">Mostra/Nascondi Campi</button>
	
	<legend>Elenco dei thread sull'argomento:</legend>
	<hr/>
	<table>
		<tr>
			<th><label style="width: 28px;">ID</label></th>
			<th colspan="1"></th>
			<th><label>Creato</label></th>
			<th><label>Domanda</label></th>
			<th><label>Risposta</label></th>
		</tr>
	<c:forEach items="${helpCenterThreadList}" var="helpcenterThread" varStatus="loop">
		<tr>
			<td>${helpcenterThread.id}</td>
			<td>
				<c:if test="${!(helpcenterThread.answer!=null)}">
					<a href="${pageContext.request.contextPath}/helpCenterThread/helpCenterThreadUpdateAnswer/${helpcenterThread.id}"><button class="btn btn-sm btn-outline-secondary" type="button">Rispondi</button></a>
				</c:if>
			</td>
			<td>${helpcenterThread.createdAt}</td>
			
			<c:set var="question" value="${helpcenterThread.question}"></c:set>
	 		<td><label class="longQ">${helpcenterThread.question}</label><label class="shortQ"> ${fn:substring(question, 0, 42)}<c:if test="${fn:length(helpcenterThread.question) gt 42}">...</c:if></label></td>
			
			<c:set var="answer" value="${helpcenterThread.answer}"></c:set>
	 		<td><label class="longA">${helpcenterThread.answer}</label><label class="shortA"> ${fn:substring(answer, 0, 42)}<c:if test="${fn:length(helpcenterThread.answer) gt 42}">...</c:if></label></td>
			
		</tr>
	</c:forEach>
	</table>
	<hr/>
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