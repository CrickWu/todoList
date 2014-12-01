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
<title>Edit Page</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>

    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
     href="css/bootstrap-datetimepicker.min.css">

    
 

	<%--
		out.print("id:" + request.getParameter("id"));
	--%>
	<%--
		out.print("name:" + request.getParameter("name"));
	--%>
	<%
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			out.print(e);
		}
		ItemService itemService = new ItemService();
		ArrayList<ItemEntry> arr = itemService.getList();
		ItemEntry thisItem = new ItemEntry();
		//out.print("size:" + arr.size());
		for (ItemEntry itemEntry : arr) {
			if (itemEntry.getId() == id) {
				thisItem = itemEntry;
				break;
			}
		}
	%>
	<%
		String nexturl;
		if (session.getAttribute("username") != null)
			nexturl = "viewTodo.jsp";
		else
			nexturl = "index.jsp";
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
					<li><a href="<%=nexturl%>">Home</a></li>
					<li><a href="register.jsp">Register</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="DealLogout">Log out</a></li>
				</ul>

			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div class="container">

		<form class="form-signin" method="post" action="DealRegister">
			<h2 class="form-signin-heading">Please modify here</h2>

			<strong>Title</strong><input type="text" name="title"
				class="form-control" placeholder="User Name" autofocus
				value=<%=thisItem.getTitleString()%>>Content<input
				type="text" name="content" class="form-control"
				placeholder="Content" autofocus
				value=<%=thisItem.getContentString()%>>Date
				<div id="datetimepicker" class="input-append date">
      <input type="text"name="date" class="form-control"
				placeholder="Date(1998-02-28)"></input>
      <span class="add-on">
        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
      </span>
    </div> Rank <input type="text"
				name="rank" class="form-control" placeholder="Rank" autofocus
				value=<%=thisItem.getRank()%>>
				
			    
       <script type="text/javascript"
     src="js/jquery.min.js">
    </script> 
    <script type="text/javascript"
     src="js/bootstrap.min.js">
    </script>
    <script type="text/javascript"
     src="js/bootstrap-datetimepicker.min.js">
    </script>
    <script type="text/javascript">
      $('#datetimepicker').datetimepicker({
        format: 'yyyy-MM-dd',
        language: 'en',
        pickDate: true,
        pickTime: true,
        hourStep: 1,
        minuteStep: 15,
        secondStep: 30,
        inputMask: true
      });
    </script>
			<br /><br/>
			<!--<div class="form-actions">
				<button class="btn btn-primary" type="submit">asdf</button>
				<button class="btn" type="submit">asdf</button>
				<button class="btn" type="button">asd</button>
			</div>-->

			<%--
				Integer message = (Integer) session.getAttribute("error");
				if (null != message)
					out.print("<div class=\"alert alert-danger\">" + message
							+ "</div>");
				session.setAttribute("error", null);
			--%>
			<input type="submit" class="btn btn-lg btn-primary btn-block"
				value="Modifty" name="modify"
				onclick="this.form.action='ModifyItem?id=<%=id%>'"> <input
				type="submit" class="btn btn-lg btn-primary btn-block"
				value="Delete" name="delete"
				onclick="this.form.action='RemoveItem?id=<%=id%>'"> <input
				type="reset" class="btn btn-lg btn-primary btn-block" value="Reset">
			<!--<button class="btn btn-lg btn-primary btn-block" type="submit">Modify</button>
			<button class="btn btn-lg btn-primary btn-block" type="reset">Reset</button>-->

		</form>

	</div>

	</div>
</body>
</html>