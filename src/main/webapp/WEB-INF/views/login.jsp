<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
	<link type="text/javascript" rel="stylesheet"
	href="<c:url value="script/loginController.js"/>" />
<%-- <script src="<c:url value="/js/bootstrap.min.js"/>" /></script> --%>
<%-- <script src="<c:url value="/js/jquery-2.1.3.min.js"/>" /></script> --%>
<%-- <script src="<c:url value="/js/angular.min.js"/>" /></script> --%>
<%-- <script src="<c:url value="/js/main.js"/>" /></script> --%>
<script src="js/bootstrap.min.js"/></script>
<script src="js/jquery-2.1.3.min.js"/></script>
<script src="js/angular.min.js"/></script>
<script src="js/main.js"/></script>
<script src="script/loginController.js"/></script>




<title>Insert title here</title>
</head>
<body>

   <div class="container col-sm-4 col-sm-offset-4" ng-controller="loginController" ng-init="init()">

      <form class="form-signin" action="validarAcesso">
      
        <h2 class="form-signin-heading text-center">Bem Vindo</h2>
        <div class="form-group"> 
			<input type="text"	class="form-control" name="login" placeholder="Usuário" required autofocus>
		</div>
		
		<div class="form-group"> 
			<input type="password"	class="form-control" name="senha" placeholder="Senha" required autofocus>
		</div>


        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-danger btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->

</body>

</html>