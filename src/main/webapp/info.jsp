<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String url = (String) session.getAttribute("url"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Information</title>
</head>
<body>
	<div id="information">
		<font color="black"> <%
 	String message = (String) session.getAttribute("info");
 	if (null != message)
 		out.print(message);
 	session.removeAttribute("info");
 %>
		</font>
	</div>
	<div id="error">
		<font color="red"> <%
 	Integer error= (Integer) session.getAttribute("error");
 	if (null != error)
 		out.print(error);
 	session.removeAttribute("error");
 %>
		</font>
	</div>
	Click <a href=<%=url%>>here</a> to return to <%=url%>.
	<%--= session.getAttribute("username") --%>
	<% session.removeAttribute("url"); %>

</body>
</html>