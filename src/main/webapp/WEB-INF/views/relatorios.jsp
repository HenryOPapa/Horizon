<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="RelatoriosController"
		ng-init="init()">


		<div class="panel panel-default">
			<div class="panel-heading accordion-heading accordion-heading-sm">
				Relatórios</div>
			<div class="panel-body">
				<div class="col-sm-4 form-group input">
					<label><strong>Filtro de Tarefa</strong></label> <select
						class="form-control" ng-model="tarefa">
						<option value="0">Orçamentos em Aberto</option>
						<option value="1">Orçamentos Aprovados</option>
						<option value="2">Orçamentos Reprovados</option>
						<option value="">...</option>
						<option value="3">Ordem de Servico Na Fila</option>
						<option value="4">Ordem de Servico Em Atendimento</option>
						<option value="5">Ordem de Servico Finalizados</option>

					</select>
				</div>



				<div class="col-sm-4 form-group input" ng-show="tarefa >2">
					<label>Filtro Funcionário</label> <select class="form-control"
						ng-model="formData.funcionario">
						<option ng-repeat="item in screenData.funcionarios"
							value="{{item.id}}">{{item.nome}}</option>
						<option value="todos">Todos</option>
					</select>
				</div>
				<div class="col-sm-12 form-group text-left" ng-cloak>
					<input type="button" class="btn btn-default bt"
						ng-click="atualizarFiltro()" value="Pesquisar">
				</div>


				<div class="col-sm-12 form-group input" ng-show="showListOrcamentos">
					<table class="table">
						<thead>
							<tr>
								<th>Código</th>
								<th>Relato</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<tr class="clickable"
								ng-repeat="row in screenData.resultadoPesquisa| filter:valorFiltro"
								ng-click="detalharOrcamento(row)" >
								<td scope="row">{{row.id_orcamento}}</td>
								<td>{{row.relato}}</td>
								<td>{{row.statusOrcamento}}</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				
				<div class="col-sm-12 form-group input" ng-show="showListOrdemServico">
					<table class="table">
						<thead>
							<tr>
								<th>Código</th>
								<th>Relato</th>
								<th>Data Criação</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<tr class="clickable"
								ng-repeat="row in screenData.resultadoPesquisa| filter:valorFiltro"
								ng-click="detalharOrcamento(row)"
								ng-show="row.idFuncionario == formData.funcionario" >
								<td scope="row">{{row.idOrdemServico}}</td>
								<td>{{row.relato}}</td>
								<td>{{row.dataCriacao}}</td>
								<td>{{row.statusOrdemServico}}</td>
							</tr>
						</tbody>
					</table>
				</div>







			</div>
		</div>
		<!-- PAINEL ESTOQUE -->

	</div>
	<!-- 
		FIM DO PAINEL DE ESTOQUE-->




	<%@ include file="../rodape.jsp"%>