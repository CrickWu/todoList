<%@page import="com.demo.dao.ItemEntry"%>
<%@page import="com.demo.service.ItemService"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery.js"></script>
<title>View Plan</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/navbar-fixed-top.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
<%
	String typeString = request.getParameter("identity");
	String keywordString = request.getParameter("keyword");
	String username = (String) session.getAttribute("username");

	String nexturl = request.getRequestURI();
	
	String homeurl = request.getParameter("username") == null ? "index.jsp" : "viewTodo.jsp";
%>
</head>
<body>
	<%
		ItemService itemService = new ItemService();

		ArrayList<ItemEntry> arr;
		arr = itemService.search(keywordString, typeString);
		int totalTask = 0;
		for (ItemEntry itemEntry : arr) {
			if (itemEntry.getUsername().equals(username)) {
				totalTask++;
			}
		}
		//out.print(username + " ");
		//out.print("size: " + arr.size() + "<br/>");
	%>


	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Plan</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a >Total entries:<span class="badge"><%=totalTask%></span></a></li>
				
					<li class="active"><a href=<%=homeurl %>>Home</a></li>
					<li><a href="register.jsp">Register</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="DealLogout">Log out</a></li>
				</ul>
				<form class="navbar-form navbar-right" method=post
					action="<%=nexturl%>">
					<div class="btn-group" data-toggle="buttons-radio">
						<button type="button" class="btn btn-primary" name="identity"
							value="rank">rank</button>
						<button type="button" class="btn btn-primary" name="identity"
							value="date">date</button>
						<button type="button" class="btn btn-primary" name="identity"
							value="id">id</button>
					</div>
					<div class="form-group">

						<input type="text" placeholder="<-- Search Order"
							class="form-control" name="keyword">
					</div>
					<input type="submit" class="btn btn-success" value="Search">
				</form>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<br>

	<div class="container">

		<%
			String message = (String) session.getAttribute("info");
			if (null != message)
				out.print("<div class=\"alert alert-warning\">" + message
						+ "</div>");
			session.removeAttribute("info");
		%>


		<%--
 	Integer error = (Integer) session.getAttribute("error");
 	if (null != error)
 		out.print("<div class=\"alert alert-danger\">" + error + "</div>");
 	session.removeAttribute("error");
 --%>
		<a class="btn btn-large btn-success" href="addItem.jsp"><span
			class="glyphicon glyphicon-plus"></span> Add a Task</a>

	</div>
	<br />
	<div class="container">
		<div class="panel panel-primary">
			<!-- Default panel contents -->
			<div class="panel-heading"><%=username%>'s Table
			</div>

			<!-- Table -->
			<table class="table">
				<thead>
					<tr>
						<th>Number</th>
						<th>Id</th>
						<th>Title</th>
						<th>Content</th>
						<th>Due Date</th>
						<th>Rank</th>
					</tr>
				</thead>
				<%
					final int ENTRIES_PER_PAGE = 50;
					int counter = 0;
					for (ItemEntry itemEntry : arr) {
						if (itemEntry.getUsername().equals(username)) {
							counter++;
							out.print("<tr>");
							out.println("<td>"
									+ counter
									+ "</td><td>"
									+ itemEntry.getId()
									+ "</td><td>"
									+ itemEntry.getTitleString()
									+ "</td><td>"
									+ itemEntry.getContentString()
									+ "</td><td>"
									+ itemEntry.getDate()
									+ "</td><td>"
									+ itemEntry.getRank()
									//+ "---username:"
									//+ itemEntry.getUsername()
									+ "</td><td><input value=\"edit\" type=\"button\" class=\"btn btn-primary\" onclick=\"window.location.href='editTodo.jsp?id="
									+ itemEntry.getId()
									+ "'\">"
									+ "</td><td>"
									+ "<input value=\"delete\" type=\"button\" class=\"btn btn-primary\" onclick=\"window.location.href='RemoveItem?id="
									+ itemEntry.getId() + "'\">" + "</td>");
							out.print("</tr>");
						}
					}
				%>
			</table>
		</div>
	</div>

</body>
</html>