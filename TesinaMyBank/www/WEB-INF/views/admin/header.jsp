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
        <li><jsp:include page="loginForm.jsp"></jsp:include></li>
        <c:choose>
        	<c:when test="${user.ruolo.id == 1}">
        		<li><jsp:include page="navbarAdmin.jsp"></jsp:include></li>
        	</c:when>
       		<c:when test="${user.ruolo.id == 2}">
        	<li><jsp:include page="navbarValidator.jsp"></jsp:include></li>
        	</c:when>
        	<c:when test="${user.ruolo.id == 3}">
        	<li><jsp:include page="navbarHelpdesk.jsp"></jsp:include></li>
        	</c:when>
        </c:choose>
      </ul>
  </div><!-- /.container-fluid -->
</nav>