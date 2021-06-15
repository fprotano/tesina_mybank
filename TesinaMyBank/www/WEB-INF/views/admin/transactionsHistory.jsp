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
<title>Storico Transazioni</title>
</head>
<body>

<jsp:include page="header.jsp"/> 
<br/>
	
	<button class="btn btn-sm btn-outline-secondary" type="button" onclick="showAndHideLongParameters()" id="preview">Mostra/Nascondi Campi</button>
	
	<legend>Storico transazioni</legend>
	<hr/>
	<table>
		<tr>
			<th><label style="width: 28px;">ID</label></th>
			<th><label style="width: 120px;">Creata in data</label></th>
			<th><label style="width: 112px;">Custom Code</label></th>
			<th><label style="width: 130px;">ID Transazione</label></th>
			<th><label>Stato</label></th>
			<th><label>Descrizione Stato</label></th>
			<th><label>Importo</label></th>
			<th><label style="width: 92px;">Da: Nome</label></th>
			<th><label>Cognome</label></th>
			<th><label style="width: 120px;">Numero Carta</label></th>
			<th><label style="width: 40px;">CIN</label></th>
			<th><label>Scadenza</label></th>
			<th><label style="width: 124px;">Id beneficiario</label></th>
			<th><label>Nome</label></th>
			<th><label>Cognome</label></th>
			<th><label>Email</label></th>
			
		</tr>
	<c:forEach items="${transactions}" var="transaction" varStatus="loop">
		<tr>
			<td><label>${transaction.id}</label></td>
			<!-- setLocale non fa niente perché la springservlet in questo caso ha la priorità e gestisce il locale -->
			<fmt:setLocale value="it_IT" /> 
			<td><label style="width: 204px;"><fmt:formatDate type="both" value="${transaction.createdAt}" pattern="E, dd/MM/yyyy, HH:mm:ss" /></label></td>
			
			<c:set var="customCode" value="${transaction.customCode}"></c:set>
	 		<td><label class="longCode">${transaction.customCode}</label><label class="shortCode"> ${fn:substring(customCode, 0, 14)}<c:if test="${fn:length(transaction.customCode) gt 14}">...</c:if></label></td>
			
			<c:set var="transactionId" value="${transaction.transactionId}"></c:set>
	 		<td><label class="longId">${transaction.transactionId}</label><label class="shortId"> ${fn:substring(transactionId, 0, 14)}<c:if test="${fn:length(transaction.transactionId) gt 14}">...</c:if></label></td>
			
			<td>${transaction.transactionStatus.title}</td>
			
			<c:set var="transactionErrorReason" value="${transaction.transactionErrorReason}"></c:set>
	 		<td><label class="longReason">${transaction.transactionErrorReason}</label><label class="shortReason"> ${fn:substring(transactionErrorReason, 0, 22)}<c:if test="${fn:length(transaction.transactionErrorReason) gt 22}">...</c:if></label></td>
			
			<td><label><fmt:formatNumber value="${transaction.amount}" maxFractionDigits="2"/></label></td>
			<td><label>${transaction.customerName}</label></td>
			<td><label>${transaction.customerSurname}</label></td>
			<td><label>${transaction.customerCreditCardNo}</label></td>
			<td><label>${transaction.customerCreditCardCin}</label></td>
			<td><label>${transaction.customerCreditCardExpiresAt}</label></td>
			<td><label>${transaction.toAccountId}</label></td>
			<td><label>${transaction.account.name}</label></td>
			<td><label>${transaction.account.surname}</label></td>
			<td><label>${transaction.account.email}</label></td>
		</tr>
	</c:forEach>
	</table>
	<hr/>
	<script>
	$(document).ready(function(){
	    $(".longId").toggle();
	    $(".longCode").toggle();
	    $(".longReason").toggle();
	    showAndHideLongParameters = function()
	    {
	        $(".shortId").toggle();
	        $(".shortCode").toggle();
	        $(".shortReason").toggle();
	        
	        $(".shortId").toggleClass("component");
	        $(".shortCode").toggleClass("component");
	        $(".shortReason").toggleClass("component");
	        
	        $(".longId").toggle();
	        $(".longCode").toggle();
	        $(".longReason").toggle();
	    }

	});
	</script>
</body>
</html>