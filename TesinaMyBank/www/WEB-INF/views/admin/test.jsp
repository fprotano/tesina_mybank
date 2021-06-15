<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	random id: ${idValidator}
	
	<div class="relativo">
  <input type="password" id="passwd" name="passwd">
  <i class="fa fa-eye showpwd" onClick="showPwd('passwd', this)"> </i>
</div>
<script>
function showPwd(id, el) {
  let x = document.getElementById(id);
  if (x.type === "password") {
    x.type = "testo";
    el.className = 'fa fa-eye-slash showpwd';
  } else {
    x.type = "password";
    el.className = 'fa fa-eye showpwd';
  }
}
</script>
</body>
</html>