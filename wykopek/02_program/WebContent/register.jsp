<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Zarejestruj</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>

	<nav class = "navbar navbar-inverse navbar-fixed-top">
       <div class="container">
         <a href="#" class="navbar-brand">Wykopek</a>
         
         <button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
           <span class="glyphicon glyphicon-list"></span>
         </button>
         
         <div class="collapse navbar-collapse navHeaderCollapse">
           <ul class="nav navbar-nav navbar-right">
             <li class="active"><a href="#">Główna</a></li>
             <li><a href="#">Dodaj</a></li>
             <li><a href="#">Zaloguj się</a></li>
           </ul>
         </div>
         
       </div>
    </nav>
    
    
	 <div class="container">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
	      <form class="form-signin" method="post" action="#">
	        <h2 class="form-signin-heading">Zarejestruj się</h2>
	        <label for="inputEmail" class="sr-only">Email adres</label>
	        <input name="inputEmail" type="email" class="form-control" placeholder="Email" required autofocus />
	        <label for="inputUsername" class="sr-only">Nazwa Uzytkownika</label>
	        <input name="inputUsername" type="text" class="form-control" placeholder="Nazwa Uzytkownika" required autofocus>
	        <label for="inputPassword" class="sr-only">Hasło</label>
	        <input name="inputPassword" type="password" class="form-control" placeholder="Hasło" required>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Zarejestruj się</button>
	        <a href="login.jsp">Zaloguj</a>
	      </form>
		</div>
    </div> <!-- /container -->
    
    <footer class="footer">
       <div class="container">
         <p class="navbar-text">Wykopek - developed by <a href="http://mgdcontrol.pl">MgdControl</a></p>
       </div>
    </footer>
     
       
	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>