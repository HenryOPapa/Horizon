<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery-2.1.3.min.js" /></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js" /></script>
<script src="js/angular.min.js" /></script>
<script src="js/main.js" /></script>
<script src="script/loginController.js" /></script>
<script>
	var RETORNO = $
	{
		result
	}
</script>



<title>Insert title here</title>
<style>

</style>
</head>
<body>

	<div class="container col-sm-4 col-sm-offset-4"
		ng-controller="LoginController" ng-init="init()">

		<form ng-con class="form-signin ">

			<h2 class="form-signin-heading text-center">Bem Vindo</h2>

			<!--Mensagem de Alert -->
						<div id="alertLogin" class="alert alert-info">
							<strong>Atenção</strong>, Digite seu Login.
						</div>
			<!--End Mensagem de Alert -->

			<div class="form-group">
				<input type="text" id="cpf" ng-model="formData.login" class="form-control"
					name="login" placeholder="Usuário">
			</div>
			
			<!--Mensagem de Alert -->
						<div id="alertSenha" class="alert alert-info">
							<strong>Atenção</strong>, Digite sua Senha.
						</div>
			<!--End Mensagem de Alert -->

			<div class="form-group">
				<input type="password" ng-model="formData.senha"
					class="form-control" name="senha" placeholder="Senha">
			</div>
			
			<!--Mensagem de Alert -->
						<div id="alertInvalido" class="alert alert-danger">
							<strong>Atenção</strong>, login ou senha inválidos.
						</div>
			<!--End Mensagem de Alert -->


			<button class="btn btn-lg btn-danger btn-block"
				ng-click="validaLogin()" type="submit">Sign in</button>
		</form>

	</div>
	<!-- /container -->

</body>

</html>