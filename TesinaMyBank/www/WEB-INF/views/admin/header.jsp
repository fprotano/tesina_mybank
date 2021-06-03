<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<body>

<jsp:include page="style.jsp"></jsp:include>

<!--  -default dopo navbar, -custom per la custom, niente per niente -->
<nav class="navbar navbar" style="background-color: rgba(233,233,233,0.5);">
  <div class="container-fluid">
    <div class="navbar-header" style="margin:8px;">
        
    <!-- per includere logo    
        <img alt="Brand" src="img/stocklogo.jpg" height="84">
    -->
    
    </div>
      <ul class="nav navbar-nav">
      
      <c:choose>
		<c:when test="${staff.id!=null}">
			<form action="MainServlet">
				<c:out value="Benvenuto, ${staff.name} ${staff.surname}"/>
				<input class="btn btn-danger" type="submit" id ="logout" name="logout" value="Logout"/>
			</form>
		</c:when>
		<c:otherwise>
			<c:out value="Effettua il login: "/>
			<jsp:include page="login.jsp"></jsp:include>
			<br/>
       </c:otherwise>
       </c:choose>
      
      <hr/> 
        
        <c:choose>
        	<c:when test="${staff.ruolo.id == 1}">
        		<li><jsp:include page="navbarAdmin.jsp"></jsp:include></li>
        	</c:when>
       		<c:when test="${staff.ruolo.id == 2}">
        	<li><jsp:include page="navbarValidator.jsp"></jsp:include></li>
        	</c:when>
        	<c:when test="${staff.ruolo.id == 3}">
        	<li><jsp:include page="navbarHelpdesk.jsp"></jsp:include></li>
        	</c:when>
        </c:choose>
      </ul>
  </div><!-- /.container-fluid -->
</nav>