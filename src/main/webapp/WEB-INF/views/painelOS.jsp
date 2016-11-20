<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="PainelOSController"
		ng-init="init()">


		<!-- INICIO  CONTAINER OS NA FILA--->
		<div class="container">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#OSTODO">O.S. na Fila</a>
						</h4>
					</div>
					<div id="OSTODO" class="panel-collapse collapse">
						<div class="panel-body">
						
						
							<!-- INICIO TABELA -->
							<div class="panel-body scrollable">
								<table class="table">
									<thead>
										<tr>
											<th>Código</th>
											<th>Relato</th>
											<th>Observação</th>
											<th>Status</th>
											<th class="text-center">Detalhar O.S.</th>
											<th class="text-center">Iniciar Tarefa</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="row in screenData.ordensDeServico"
											ng-click="" ng-show="row.statusOrdemServico == 'TODO' && row.idFuncionario === screenData.dadosPadraoVO.funcionario.id">
											<td scope="row">{{row.idOrdemServico}}</td>
											<td>{{row.relato}}</td>
											<td>{{row.observacao}}</td>
											<td>{{row.statusOrdemServico}}</td>
											<td class="text-center"><span
												class="glyphicon glyphicon-plus text-primary clickable"
												ng-click=""></span></td>
											<td class="text-center"><span
												class="glyphicon glyphicon-play text-primary clickable"
												ng-click="iniciarTarefa(row)"></span></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- FIM TABELA -->
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- FIM  CONTAINER OS NA FILA--->
		
		<!-- INICIO  CONTAINER OS NA FILA--->
		<div class="container">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#OSWIP">O.S. em Andamento</a>
						</h4>
					</div>
					<div id="OSWIP" class="panel-collapse collapse">
						<div class="panel-body">
							<!-- INICIO TABELA -->
							<div class="panel-body scrollable">
								<table class="table">
									<thead>
										<tr>
											<th>Código</th>
											<th>Relato</th>
											<th>Observação</th>
											<th>Status</th>
											<th class="text-center">Detalhar O.S.</th>
										</tr>
									</thead>
									<tbody>
										<tr class="clickable" ng-repeat="row in screenData.ordensDeServico"
											ng-click="" ng-show="row.statusOrdemServico == 'WIP' && row.idFuncionario === screenData.dadosPadraoVO.funcionario.id">
											<td scope="row">{{row.idOrdemServico}}</td>
											<td>{{row.relato}}</td>
											<td>{{row.observacao}}</td>
											<td>{{row.statusOrdemServico}}</td>
											<td class="text-center"><span
												class="glyphicon glyphicon-plus text-primary clickable"
												ng-click=""></span></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- FIM TABELA -->
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- FIM  CONTAINER OS NA FILA--->
		
		<!-- INICIO  CONTAINER OS NA FILA--->
		<div class="container">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#OSDONE">O.S. Finalizadas</a>
						</h4>
					</div>
					<div id="OSDONE" class="panel-collapse collapse">
						<div class="panel-body">
							<!-- INICIO TABELA -->
							<div class="panel-body scrollable">
								<table class="table">
									<thead>
										<tr>
											<th>Código</th>
											<th>Relato</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
										<tr class="clickable" ng-repeat="row in screenData.ordensDeServico"
											ng-click="" ng-show="row.statusOrdemServico == 'DONE' && row.idFuncionario === screenData.dadosPadraoVO.funcionario.id">
											<td scope="row">{{row.id_orcamento}}</td>
											<td>{{row.relato}}</td>
											<td>{{row.statusOrdemServico}}</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- FIM TABELA -->
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- FIM  CONTAINER OS NA FILA--->


	</div>




	<%@ include file="../rodape.jsp"%>