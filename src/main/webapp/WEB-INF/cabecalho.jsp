<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Horizon</title>
<script src="js/jquery-2.1.3.min.js" /></script>
<script src="js/jquery.maskedinput.min.js" /></script>
<script src="js/mask.js" /></script>
<script src="js/bootstrap.min.js" /></script>
<script src="js/angular.min.js" /></script>
<script src="js/main.js" /></script>
<script src="js/jquery.maskMoney.js" /></script>
<link href="css/main.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/kanban.css" rel="stylesheet">

<script>
var RETORNO = ${result}
</script>

<header> <nav class="navbar navbar-inverse">
<div class="container">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="sr-only"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand  glyphicon glyphicon-home" href="menu"> <label>Horizon</label>
		</a>
	</div>


	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" ng-controller="MenuController">
		<ul class="nav navbar-nav">
			<!-- 				<li><a href="#"><label>Novo Orcamento</label></a></li> -->


			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"><label>Cliente</label> <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="cadastroCliente">Novo Cliente</a></li>
					<li><a href="listaClientes">Lista de Clientes</a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"><label>Funcion�rio</label><span
					class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="cadastroFuncionario">Novo Funcion�rio</a></li>
					<li><a href="listaFuncionarios">Lista de Funcion�rios</a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"><label>Pain�is</label><span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="manutencaoOrcamento">Or�amento</a></li>
					<li><a href="aprovarReprovarOrcamento">Aprovar / Reprovar Or�amento</a></li>
					<li><a href="painelOS">Ordem de Servi�o</a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"><label>Controle</label><span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="especialidade">Especialidades</a></li>
					<li><a href="estoque">Estoque</a></li>
					<li><a href="itensDeServico">Cadastrar Itens de Servi�o</a></li>
					<li><a href="relatorios">Relat�rios</a></li>
				</ul></li>
		</ul>


		<ul class="nav navbar-nav navbar-right">

			<li><a href="novoOrcamento"><label>Novo Orcamento</label></a></li>

			<li><span><input type="text" placeholder="Busca R�pida"
					class="form-control mTop10"></span></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"><span class="glyphicon glyphicon-cog"></span></a>
				<ul class="dropdown-menu">
					<li ng-click="alterarSenha()"><a>Alterar Senha</a></li>
					<li ng-click="logout()"><a>Sair do Sistema</a></li>
				</ul></li>
			<li><strong>{{screenData.dadosPadraoVO.funcionario.nome}}</strong></li>
		</ul>


	</div>
	<!-- /.navbar-collapse -->
</div>
<!-- /.container-fluid --> </nav> </header>
</head>