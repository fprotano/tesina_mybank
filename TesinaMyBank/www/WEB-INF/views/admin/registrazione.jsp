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

<form:form method="post" modelAttribute="staffRegistrato" action="${pageContext.request.contextPath}/staff/registrazione">

<p>Registra un nuovo membro dello Staff</p>


<label>Nome</label>
<br/>
<form:input path="name" />
<br/>
<label>cognome</label>
<br/>
<form:input path="surname" />
<br/>
<label>Email: </label>
<br/>
<form:input path="email" />
<br/>
<label>Password: </label>
<br/>
<form:input path="password" />
<hr/>
<form:select path="roleId">
<option value='1'> Amministratore </option>
<option value='2'>Verificatore</option>
<option value='3'>Help Desk</option>
</form:select>
<input type="submit" value="registrati">
</form:form>
















</body>
</html>