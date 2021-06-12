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
<title>Transactions</title>
</head>
<body>
	 <jsp:include page="header.jsp"/> 
	 <br/>
	 
	<button class="btn btn-sm btn-outline-secondary" type="button" onclick="showAndHideTransactionId()" id="preview">Mostra/Nascondi ID transazioni</button>
	
	<legend>Dettagli transazioni da Autorizzare</legend>
	<hr/>
	<table>
		<tr>
			<th>ID</th>
			<th>Creata in data</th>
			<th>customCode</th>
			<th>transactionId</th>
			<th>Stato</th>
			<th>Importo</th>
			<th>Richied. Nome</th>
			<th>Cognome</th>
			<th>Numero Carta</th>
			<th>CIN Carta</th>
			<th>Scadenza</th>
			<th>Benef. Id</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Email</th>
			
		</tr>
	<c:forEach items="${transactions}" var="transaction" varStatus="loop">
		<tr>
			<td>${transaction.id}</td>
			<!-- setLocale non fa niente perché la springservlet in questo caso ha la priorità e gestisce il locale -->
			<fmt:setLocale value="it_IT" /> 
			<td><fmt:formatDate type="both" value="${transaction.createdAt}" pattern="E, dd-MMM-yyyy, HH:mm:ss" /></td>
			<td>${transaction.customCode}</td>
			
			<c:set var="transactionId" value="${transaction.transactionId}"></c:set>
	 		<td><label class="longId">${transaction.transactionId}</label><label class="shortId"> ${fn:substring(transactionId, 0, 14)}...</label></td>
			
			<td>${transaction.transactionStatus.title}</td>
			<td><fmt:formatNumber value="${transaction.amount}" maxFractionDigits="2"/></td>
			<td>${transaction.customerName}</td>
			<td>${transaction.customerSurname}</td>
			<td>${transaction.customerCreditCardNo}</td>
			<td>${transaction.customerCreditCardCin}</td>
			<td>${transaction.customerCreditCardExpiresAt}</td>
			<td>${transaction.toAccountId}</td>
			<td>${transaction.account.name}</td>
			<td>${transaction.account.surname}</td>
			<td>${transaction.account.email}</td>
		</tr>
	</c:forEach>
	</table>
	<hr/>
	<script>
	$(document).ready(function(){
	    $(".longId").toggle();
	    showAndHideTransactionId = function()
	    {
	        $(".shortId").toggle();
	        $(".shortId").toggleClass("component");
	        $(".longId").toggle();
	    }

	});
	</script>
</body>
</html>