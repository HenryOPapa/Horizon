<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery-2.1.3.min.js" /></script>
<script src="js/angular.min.js" /></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js" /></script>
<script src="js/main.js" /></script>
<script src="script/pecaController.js" /></script>
<script>
var RETORNO = ${result}
</script>

<title>Insert title here</title>
</head>
<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="PecaController" ng-init="init()">
		<form>
			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Peças para manutenção</div>
				<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th>Descrição</th>
							<th>Valor</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in screenData.pecas">
							<td  scope="row">{{row.descricao}}</td>
							<td>{{row.valor}}</td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>



			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">Simulação
					Nova Peça</div>
				<div class="panel-body">

					<div class="col-sm-3 form-group">
						<strong>Descrição: </strong>
					</div>

					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="descricao"
							ng-model="formData.descricao">
					</div>

					<div class="col-sm-3 form-group">
						<strong>Valor: </strong>
					</div>

					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="valor"
							ng-model="formData.valor">
					</div>

					<div class="col-sm-12 form-group text-left" ng-cloak>
						<input type="button" class="btn btn-default bt"
							ng-click="adicionaPeca()" value="Confirmar">
					</div>

				</div>
			</div>



		</form>


	</div>
</body>
<%@ include file="../rodape.jsp"%>
</html>