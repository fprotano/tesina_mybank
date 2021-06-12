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
<title>Admin Home</title>
</head>
<body>
	
 	<jsp:include page="header.jsp"/> 
 	
	
<fieldset>
	<b>Nome: ${staff.name}</b>
	<br>
	<b>Cognome: ${staff.surname}</b>
	<br>
	<b>Email: ${staff.email}</b>
	<br>
	<b>Password: </b><button class="btn btn-sm btn-outline-secondary" type="button" onclick="newPassFunction()">Modifica</button></a>
	<c:if test="${passwordUpdated!=null}">
		<c:choose>
			<c:when test="${passwordUpdated==0}">
			<br/>
			<c:out value="Password aggiornata effettuato con successo."></c:out>
			</c:when>
			<c:when test="${passwordUpdated==1}">
			<br/>
			<c:out value="Qualcosa è andato storto. Riprova"></c:out>
			</c:when>
		</c:choose>
	</c:if>
	<br>
	<b>Ruolo: ${staff.role.title}</b>
</fieldset>



<script>
function newPassFunction() {
	var newPass = prompt("Inserisci nuova password");
	if(newPass.length>4){
	$.ajax({
		  type: "POST",
		  url: "http://localhost:8080/TesinaMyBank/staff/updatePassword/"+newPass,
		  success: function(response){
			  console.log(response);
			  if(response==1) {
				  window.location.href="http://localhost:8080/TesinaMyBank/staff/home";  
			  }
		  }
	})} else {
		alert("Inserisci una password con più di 4 caratteri.");
	};
}
</script>

</body>
</html>