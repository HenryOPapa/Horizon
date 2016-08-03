<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery-2.1.3.min.js"/></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"/></script>
<script src="js/angular.min.js"/></script>
<script src="js/main.js"/></script>
<script src="script/loginController.js"/></script>
<script>
var RETORNO = ${result}
</script>



<title>Insert title here</title>
</head>
<body>

   <div class="container col-sm-4 col-sm-offset-4" ng-controller="LoginController" ng-init="init()">

      <form ng-con class="form-signin">
      
        <h2 class="form-signin-heading text-center">Bem Vindo</h2>
        <div class="form-group"> 
			<input type="text" ng-model="formData.login" class="form-control" name="login" placeholder="Usuário" required autofocus>
		</div>
		
		<div class="form-group"> 
			<input type="password"	ng-model="formData.senha" class="form-control" name="senha" placeholder="Senha" required autofocus>
		</div>


        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-danger btn-block" ng-click="validaLogin()" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->

</body>

</html>