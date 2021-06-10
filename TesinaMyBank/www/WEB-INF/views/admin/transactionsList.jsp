<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transactions</title>
</head>
<body>
	<p>esisto, transactionsList</p>
	<fieldset>
	<table>
		<tr>
			<th>ID</th>
			<th>createdAt</th>
			<th>customCode</th>
			<th>transactionId</th>
			<th>transactionStatusId</th>
			<th>Amount</th>
			<th>toAccountId</th>
			
		</tr>
	<c:forEach items="${transactions}" var="transaction" varStatus="loop">
		<tr>
			<td>${transaction.id}</td>
			<td>${transaction.createdAt}</td>
			<td>${transaction.customCode}</td>
			<td>${transaction.transactionId}</td>
			<td>${transaction.transactionStatusId}</td>
			<td>${transaction.amount}</td>
			<td>${transaction.toAccountId}</td>
		</tr>
	</c:forEach>
	</table>
	</fieldset>
</body>
</html>