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
	
	<button class="btn btn-sm btn-outline-secondary" type="button" onclick="showAndHideLongParameters()" id="preview">Mostra/Nascondi Campi</button>
	
	<legend>Dettagli transazioni da Autorizzare</legend>
	<hr/>
	<table>
		<tr>
			<th><label style="width: 28px;">ID</label></th>
			<th colspan="2"><label>Gestisci transazioni</label></th>
			<th><label style="width: 120px;">Creata in data</label></th>
			<th><label style="width: 112px;">Custom Code</label></th>
			<th><label style="width: 130px;">ID Transazione</label></th>
			<th><label>Stato</label></th>
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
			<td>${transaction.id}</td>
			
			
			<td>
				<a href="${pageContext.request.contextPath}/externalTransaction/acceptTransaction/${transaction.id}"><button class="btn btn-sm btn-outline-success" type="button">Accetta</button></a>
			</td>
			<td>
				<%-- <a href="${pageContext.request.contextPath}/staff/refuseTransaction/${transaction.id}"><button class="btn btn-sm btn-outline-danger" type="button">Rifiuta</button></a> --%>
				<button class="btn btn-sm btn-outline-danger" type="button" onclick="addRefuseDescription(${transaction.id})">Rifiuta</button>
			</td>
			
			
			<!-- setLocale non fa niente perch� la springservlet in questo caso ha la priorit� e gestisce il locale -->
			<fmt:setLocale value="it_IT" /> 
			<td><label style="width: 204px;"><fmt:formatDate type="both" value="${transaction.createdAt}" pattern="E, dd/MM/yyyy, HH:mm:ss" /></label></td>
			
			<c:set var="customCode" value="${transaction.customCode}"></c:set>
	 		<td><label class="longCode">${transaction.customCode}</label><label class="shortCode"> ${fn:substring(customCode, 0, 14)}<c:if test="${fn:length(transaction.customCode) gt 14}">...</c:if></label></td>
			
			<c:set var="transactionId" value="${transaction.transactionId}"></c:set>
	 		<td><label class="longId">${transaction.transactionId}</label><label class="shortId"> ${fn:substring(transactionId, 0, 14)}<c:if test="${fn:length(transaction.transactionId) gt 14}">...</c:if></label></td>
			
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
	    $(".longCode").toggle();
	    showAndHideLongParameters = function()
	    {
	        $(".shortId").toggle();
	        $(".shortCode").toggle();
	        $(".shortId").toggleClass("component");
	        $(".shortCode").toggleClass("component");
	        $(".longId").toggle();
	        $(".longCode").toggle();
	    }

	});
	</script>
	<script>
	function addRefuseDescription(transactionId) {
		var refuseDescription = prompt("Inserisci il motivo del rifiuto della transazione:");
		if(refuseDescription.length>1 && refuseDescription.length<256){
		$.ajax({
			  type: "POST",
			  url: "http://localhost:8080/TesinaMyBank/externalTransaction/refuseTransaction/"+transactionId+"/"+refuseDescription,
			  success: function(response){
				  console.log(response);
				  if(response==0) {
					  window.location.href="http://localhost:8080/TesinaMyBank/externalTransaction/transactionsList";  
				  }
				  if(response==1) {
					  alert("Non � stato possibile aggiornare la transazione. Controllare la connessione.");  
				  }
			  }
		})} else {
			alert("Inserisci una descrizione non vuota di massimo 255 caratteri");
		};
	}
	</script>
</body>
</html>