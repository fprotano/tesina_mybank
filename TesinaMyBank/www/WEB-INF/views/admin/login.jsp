<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

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
















</body>
</html>