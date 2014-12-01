<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- session.setAttribute("username", "big"); --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
    
    <% String nexturl;
    	if (session.getAttribute("username") != null)
    	nexturl = "viewTodo.jsp";
    	else nexturl = "index.jsp";%>
</head>
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
					<li ><a href="<%=nexturl%>">Home</a></li>
					<li class="active"><a href="register.jsp">Register</a></li>
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
			<h2 class="form-signin-heading">Please register here</h2>
			<input type="text" class="form-control" placeholder="User Name"
				autofocus name="user"> <input type="password"
				class="form-control" placeholder="Password" name="password">


			<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
				<button class="btn btn-lg btn-primary btn-block" type="reset">Reset</button>
		</form>
	</div>
</body>
</html>