<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%
String cp = request.getContextPath();
%>
<jsp:include page="templates/header.jsp">
<jsp:param value="Index" name="title"/>
</jsp:include>
It works!
<jsp:include page="templates/footer.jsp"></jsp:include>