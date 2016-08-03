<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-2.1.3.min.js"/></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"/></script>
<script src="js/angular.min.js"/></script>
<script src="js/main.js"/></script>
<script src="script/loginController.js"/></script>
</head>
<body>

<header> <nav class="navbar navbar-inverse">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand  glyphicon glyphicon-home" href="index.jsp">
				<label>Horizon</label>
			</a>
		</div>


		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
<!-- 				<li><a href="#"><label>Novo Orcamento</label></a></li> -->


				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><label>Cliente</label> <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="teste.jsp">Novo Cliente</a></li>
						<li><a href="#">Lista de Clientes</a></li>
						<li><a href="#">Novo Equipamento</a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><label>Funcion�rio</label><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Novo Funcion�rio</a></li>
						<li><a href="#">Lista de Funcion�rios</a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><label>Pain�is</label><span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="">Or�amento</a></li>
						<li><a href="#">Ordem de Servi�o</a></li>
					</ul></li>
					
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><label>Controle</label><span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Criar especialidade</a></li>
						<li><a href="cadastroPeca">Adicionar Pe�a</a></li>
						<li><a href="#">Adicionar Servi�o</a></li>
						<li><a href="#">Relat�rios</a></li>
					</ul></li>
			</ul>



			<ul class="nav navbar-nav navbar-right">
			
			<li><a href="#"><label>Novo Orcamento</label></a></li>
			
			<li><span><input type="text" placeholder="Busca R�pida" class="form-control mTop10"></span></li>
			
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><span class="glyphicon glyphicon-cog"></span></a>
					<ul class="dropdown-menu">
						<li><a href="teste.jsp">Alterar Senha</a></li>
						<li><a href="#">Sair do Sistema</a></li>
					</ul></li>
			</ul>


		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav> </header>

</body>
</html>