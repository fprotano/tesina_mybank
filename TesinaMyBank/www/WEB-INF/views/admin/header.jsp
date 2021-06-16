<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>

<jsp:include page="style.jsp"></jsp:include>

<!--  -default dopo navbar, -custom per la custom, niente per niente -->
<nav class="navbar navbar-light">
  <div class="container-fluid">
    <div class="navbar-header" style="margin:8px;">
        
    <!-- per includere logo    
        <img alt="Brand" src="img/stocklogo.jpg" height="84">
    -->
    
    </div>
    	 <!-- mr-auto per l'alignmet a sinistra automatico  -->
      <ul class="nav navbar-nav mr-auto">
      
      <c:choose>
		<c:when test="${staff.id!=null}">
			<h5>Benvenuto,</h5>
			<h5>hai effettuato il login come <b>${staff.name} ${staff.surname}</b>, con la qualifica di <b>${fn:toLowerCase(staff.role.title)}</b>.</h5>
			<a href="${pageContext.request.contextPath}/staff/logout"><button class="btn btn-sm btn-outline-danger" type="button">Logout</button></a>
			
		</c:when>
		<c:otherwise>
			<c:out value="Effettua il login: "/>
			<jsp:include page="login.jsp"></jsp:include>
			<br/>
       </c:otherwise>
       </c:choose>
      
      <hr/> 
        
        <c:choose>
        	<c:when test="${staff.roleId == 1}">
        		<li><jsp:include page="navbarAdmin.jsp"></jsp:include></li>
        	</c:when>
       		<c:when test="${staff.roleId == 2}">
        	<li><jsp:include page="navbarValidator.jsp"></jsp:include></li>
        	</c:when>
        	<c:when test="${staff.roleId == 3}">
        	<li><jsp:include page="navbarHelpdesk.jsp"></jsp:include></li>
        	</c:when>
        </c:choose>
      </ul>
  </div><!-- /.container-fluid -->
</nav>
