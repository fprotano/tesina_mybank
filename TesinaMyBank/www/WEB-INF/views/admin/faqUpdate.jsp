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
<title>Modifica FAQ</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
<br/>

<form:form method="post" modelAttribute="faqToUpdate" action="${pageContext.request.contextPath}/faq/updateFaq">

<h4>Stai modificando la FAQ con ID: ${faqToUpdate.id}</h4>
<div style="margin: auto;width: 100%;">
	<form:input hidden="hidden" path="id"/>
	<label>Domanda:</label>
	<br/>
	<form:textarea style="width:50%; height:120px;" path="question"/>
	<br/>
	<label>Risposta:</label>
	<br/>
	<form:textarea style="width:50%; height:320px;" path="answer"/>
	<hr/>
	<input type="submit" value="Update">
	</form:form>
</div>

</body>
</html>