<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transactions</title>
</head>
<body>
	 
	<legend>Dettagli transazioni da Autorizzare</legend>
	<fieldset>
	<table>
		<tr>
			<th>ID</th>
			<th>createdAt</th>
			<th>customCode</th>
			<th>transactionId</th>
			<th>Stato</th>
			<th>Amount</th>
			<th>toAccountId</th>
			
		</tr>
	<c:forEach items="${transactions}" var="transaction" varStatus="loop">
		<tr>
			<td>${transaction.id}</td>
			<fmt:setLocale value="it_IT" /> 
			<td><fmt:formatDate type="both" value="${transaction.createdAt}" pattern="E, dd-MMM-yyyy, HH:mm:ss" /></td>
			<td>${transaction.customCode}</td>
			<td>${transaction.transactionId}</td>
			<td>${transaction.transactionStatus.title}</td>
			<td><fmt:formatNumber value="${transaction.amount}" maxFractionDigits="2"/></td>
			<td>${transaction.toAccountId}</td>
		</tr>
	</c:forEach>
	</table>
	</fieldset>
</body>
</html>