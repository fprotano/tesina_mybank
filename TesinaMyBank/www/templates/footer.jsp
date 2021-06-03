<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

</div>
<footer  class="container">
<a href="${pageContext.request.contextPath}/index?lang=it_IT">Italiano</a>
| <a href="${pageContext.request.contextPath}/index?lang=en_EN">Inglese</a>
| Lingua corrente : ${pageContext.response.locale}
</footer>
</body>
</html>