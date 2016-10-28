<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">


<title>Insert title here</title>
</head>
<body>

<header>
<nav class="navbar navbar-inverse">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand  glyphicon glyphicon-home" href="index.jsp">
      <label>Horizon</label></a>
    </div>


    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav"> 
        <li><a href="#"><label>Novo Orcamento</label></a></li>    
           
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><label>Cliente</label> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="teste.jsp">Novo Cliente</a></li>
            <li><a href="#">Lista de Clientes</a></li>
            <li><a href="#">Novo Equipamento</a></li>
          </ul>
        </li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><label>Funcionário</label><span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="teste.jsp">Novo Funcionário</a></li>
            <li><a href="#">Lista de Funcionários</a></li>
          </ul>
        </li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-cog"></span></a>
          <ul class="dropdown-menu">
            <li><a href="teste.jsp">Alterar Senha</a></li>
            <li><a href="#">Sair do Sistema</a></li>
          </ul>
        </li>
      </ul>


    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</header>
<main>
<h2>MENU</h2>
<h2>MENU</h2>
<h2>MENU</h2>
<h2>MENU</h2>
<h2>MENU</h2>
<h2>MENU</h2>
<h2>MENU</h2>
<h2>MENU</h2>
<h2>MENU</h2>
<h2>MENU</h2>
<h2>MENU</h2>
</main>

	<div class="footer-black">
		<div class="container">
			<p class="text-muted">Copyright © Horizon 2016</p>
			<ul class="nav pull-right scroll-top">
				<li><a href="#" title="Scroll to top"><i
						class="glyphicon glyphicon-chevron-up"></i></a></li>
			</ul>
			<div class="container">
				<p class="text-muted"></p>
			</div>
		</div>
	</div>


</body>
<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</html>