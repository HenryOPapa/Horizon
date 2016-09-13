<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="EstoqueController"
		ng-init="init()">


		<div class="panel panel-default">
			<div class="panel-heading accordion-heading accordion-heading-sm">
				Estoque</div>
			<div class="panel-body">


				<div class="col-sm-3 form-group">
					<input type="text" class="form-control" name="notaFiscal"
						ng-model="formData.notaFiscal" placeholder="Número Nota Fiscal">
				</div>


				<div class="col-sm-4 form-group input">
					<select class="form-control" ng-model="formData.pecaSelecionada">
						<option value="">Peças</option>
						<option ng-repeat="item in screenData.pecas"
							value="{{item.idPeca}}">{{item.descricao}}</option>
					</select>
				</div>


				<div class="col-sm-3 form-group">
					<input type="number" class="form-control" name="quantidade"
						ng-model="formData.quantidade" placeholder="Quantidade">
				</div>

				<div class="col-sm-12 form-group text-left" ng-cloak>
					<input type="button" class="btn btn-default bt"
						ng-click="atualizarEstoque()" value="Incluir">
				</div>

			</div>
		</div>

		<div id="cadastroSucesso"
			class="col-sm-12 form-group text-left alert alert-success" ng-cloak>
			Item adicionado ao estoque.</div>

		<div id="cadastroErro"
			class="col-sm-12 form-group text-left alert alert-danger" ng-cloak>
			Erro ao cadastrar a peça.</div>
		<!-- PAINEL ESTOQUE -->

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
				Peças em estoque</div>

			<div class="panel-body scrollable">

				<table class="table">
					<thead>
						<tr>
							<th>Descrição</th>
							<th>Quantidade</th>
							<th class="text-center">Editar</th>

						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="row in screenData.estoque | filter:valorFiltro">
							<td scope="row">{{row.descricaoPeca}}</td>
							<td>{{row.quantidade}}</td>

							<td class="text-center"><span
								class="glyphicon glyphicon-pencil text-primary clickable"
								ng-click="editarPeca(row)"></span></td>

						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 
		FIM DO PAINEL DE ESTOQUE-->




	<%@ include file="../rodape.jsp"%>