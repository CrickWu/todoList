<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	
%>
<html>
<head>
<title>Login Here</title>
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
					<li class="active"><a href="index.jsp">Home</a></li>
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

		<form class="form-signin" method="post" action="DealLogin">
			<h2 class="form-signin-heading">Please sign in</h2>
			<input type="text" class="form-control" placeholder="User Name"
				autofocus name="username"> <input type="password"
				class="form-control" placeholder="Password" name="password">
			<label class="checkbox"> <input type="checkbox"
				value="remember-me" name="remember"> Remember me
			</label>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
			<label> <a href="register.jsp">Register</a><br />
			</label>
			
				 <%
 	String username = (String) session.getAttribute("username");
 	if (null != username) {
 		out.print(username);
 		response.sendRedirect("DealLogin");
 	}

 	//Integer message = (Integer) session.getAttribute("error");
 	//if (null != message)
 		//out.print("<div class=\"alert alert-danger\"><strong> Message: </strong>" + message +"</div>");
 	session.removeAttribute("error");
 	String infor = (String) session.getAttribute("info");
 	if (null != infor)
 		out.print("<div class=\"alert alert-danger\"><strong> Message: </strong>" + infor +"</div>");
 	session.removeAttribute("info");
 %>
				
			
		</form>
	</div>
	<!-- /container -->
	<!-- 
	<form method="post" action="DealLogin">
		User Name<br /> <input type="text" name="username" /> <br />
		Password<br /> <input type="password" name="password" /> <br /> <input
			type="submit" value="Login" /> Remember me <input type="checkbox"
			name="remember">
	</form>

	<a href="register.jsp">Register</a>
	<div id="error">
		<font color="red"> 
		 -->

</body>
</html>