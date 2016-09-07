<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="EspecialidadeController" ng-init="init()">
		<form>

			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Nova Peça</div>
				<div class="panel-body">

					<div class="col-sm-3 form-group">
						<strong>Descrição: </strong>
					</div>

					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="descricao"
							ng-model="formData.descricao">
					</div>


					<div class="col-sm-12 form-group text-left" ng-cloak>
						<input type="button" class="btn btn-default bt"
							ng-click="adicionaEspecialidade()" value="Confirmar">
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
					Especialidades Cadastradas</div>
				<div class="panel-body scrollable">
					<table class="table table table-striped table-hover scrollable">
						<thead>
							<tr>
								<th>Descrição</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="row in screenData.especialidades | filter:valorFiltro">
								<td scope="row">{{row.descricao}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</form>

		<div id="cadastroSucesso"
			class="col-sm-12 form-group text-left alert alert-success" ng-cloak>
			Especialidade cadastrada com sucesso.</div>

		<div id="cadastroErro"
			class="col-sm-12 form-group text-left alert alert-danger" ng-cloak>
			Erro ao cadastrar a especialidade.</div>


	</div>

	<%@ include file="../rodape.jsp"%>