<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>

<%-- this is the test name setting which needs to be replaced by another login term --%>
<%-- session.setAttribute("username", "testname"); --%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Page</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->


    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
     href="css/bootstrap-datetimepicker.min.css">
</head>
<%
		String nexturl;
		if (session.getAttribute("username") != null)
			nexturl = "viewTodo.jsp";
		else
			nexturl = "index.jsp";
	%>
<body>

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

		<form class="form-signin" method="post" action="DealItem">
			<h2 class="form-signin-heading">Add here</h2>

			<strong>Title</strong><input type="text" name="title"
				class="form-control" placeholder="User Name" autofocus
				>Content<input
				type="text" name="content" class="form-control"
				placeholder="Content" autofocus
				>Date
				<div id="datetimepicker" class="input-append date">
      <input type="text"name="date" class="form-control"
				placeholder="Date(1998-02-28)"></input>
      <span class="add-on">
        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
      </span>
    </div> Rank <input type="text"
				name="rank" class="form-control" placeholder="Rank" autofocus
				>
				
			    
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
				value="Add" name="submit"
			> <input
				type="reset" class="btn btn-lg btn-primary btn-block" value="Reset">
			<!--<button class="btn btn-lg btn-primary btn-block" type="submit">Modify</button>
			<button class="btn btn-lg btn-primary btn-block" type="reset">Reset</button>-->

		</form>

	</div>

	</div>

</body>
</html>