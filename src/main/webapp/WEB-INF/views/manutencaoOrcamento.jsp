
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="ManutencaoOrcamentoController"
		ng-init="init()">

		<!-- INICIO PAINEL DE ORCAMENTOS -->
		<div class="input-group">
			<div class="input-group-addon">
				<i class="glyphicon glyphicon-search"></i>
			</div>
			<input type="text" class="form-control" placeholder="Pesquisar"
				ng-model="valorFiltro" placeholder="Procurar" id="pesquisa">
		</div>


		<div class="panel panel-default">
			<div class="panel-heading accordion-heading accordion-heading-sm">
				Orcamentos</div>

			<div class="panel-body scrollable">

				<table class="table" >
					<thead>
						<tr>
							<th>Código</th>
							<th>Relato</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody >
						<tr class="clickable" ng-repeat="row in screenData.orcamentos | filter:valorFiltro"
							ng-click="detalharOrcamento(row)">
							<td scope="row">{{row.id_orcamento}}</td>
							<td>{{row.relato}}</td>
							<td>{{row.statusOrcamento}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>


	<!-- FIM PAINEL LOCALIZA CLIENTE -->

	</div>

	<%@ include file="../rodape.jsp"%>