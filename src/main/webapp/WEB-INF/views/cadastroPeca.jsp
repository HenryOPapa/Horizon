<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="PecaController" ng-init="init()">
		<form>

			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Nova Pe�a</div>
				<div class="panel-body">

					<div class="col-sm-3 form-group">
						<strong>Descri��o: </strong>
					</div>

					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="descricao"
							ng-model="formData.descricao">
					</div>

					<div class="col-sm-3 form-group">
						<strong>Valor: </strong>
					</div>

					<div class="col-sm-3 form-group">
						<input type="text" class="form-control dinheiro" name="valor"
							ng-model="formData.valor">
					</div>

					<div class="col-sm-12 form-group text-left" ng-cloak>
						<input type="button" class="btn btn-default bt"
							ng-click="adicionaPeca()" value="Confirmar">
					</div>

				</div>
			</div>

			<div class="input-group">
				<div class="input-group-addon">
					<i class="glyphicon glyphicon-search"></i>
				</div>
				<input type="text" class="form-control" placeholder="Pesquisar"
					ng-model="valorFiltro" placeholder="Procurar" id="pesquisa"
					ng-model="valorFiltro">
			</div>
			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Pe�as para manuten��o</div>
				<div class="panel-body scrollable">
					<table class="table table table-striped table-hover scrollable">
						<thead>
							<tr>
								<th>Descri��o</th>
								<th>Valor</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="row in screenData.pecas | filter:valorFiltro">
								<td scope="row">{{row.descricao}}</td>
								<td>{{row.valor}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</form>

		<div id="cadastroSucesso"
			class="col-sm-12 form-group text-left alert alert-success" ng-cloak>
			Pe�a cadastrada com sucesso.</div>

		<div id="cadastroErro"
			class="col-sm-12 form-group text-left alert alert-danger" ng-cloak>
			Erro ao cadastrar a pe�a.</div>


	</div>

	<%@ include file="../rodape.jsp"%>