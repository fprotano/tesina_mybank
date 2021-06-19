<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
String cp = request.getContextPath();
%>
<!-- // nel login voglio far apparire solo il login.  Una volta dentro, mostro l'header -->
<jsp:include page="../../../templates/header.jsp">
<jsp:param value="Index" name="title"/>
</jsp:include>

<form:form method="post" modelAttribute="staff" action="${pageContext.request.contextPath}/staff/login">

<label>Email: </label>
<br/>
<form:input path="email" />
<br/>
<label>Password: </label>
<br/>
<form:input path="password" />
<hr/>
<input type="submit" value="Login">
</form:form>
<c:if test="${staff.name!=null}">
<div>
</div>
<script>
var OTP = prompt("Inserisci otp");
if(true){	// true è placeholder
$.ajax({
	  type: "POST",
	  url: "http://localhost:8080/TesinaMyBank/staff/confermaOTP/"+OTP,
	  success: function(response){
		  console.log(response);
		  if(response==1) {
			  window.location.href="http://localhost:8080/TesinaMyBank/staff/home";  
		  } else if(response==2){
			  alert("Il Codice OTP è scaduto. Riprova.");
		  } else if(response==0){
			  alert("IL Codice OTP è errato. Riprova.")
		  }
	  }
	})};
</script>
</c:if>

<jsp:include page="../../../templates/footer.jsp"></jsp:include>