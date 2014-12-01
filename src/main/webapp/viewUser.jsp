<%@page import="com.demo.dao.UserEntry"%>
<%@page import="com.demo.service.UserService"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View User</title>
</head>
<body>
<%
	UserService userService = new UserService();
ArrayList<UserEntry> arr = userService.getList();
for (UserEntry userEntry : arr) {
	out.println(userEntry.getId() + " " + userEntry.getUser() + " " + userEntry.getPassword() + "<br/>");
}
%>

</body>
</html>