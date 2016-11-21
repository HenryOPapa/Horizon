<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="PainelOSController"
		ng-init="init()">


		<!-- INICIO  CONTAINER OS NA FILA--->
		<div class="container" ng-show="showListaTarefas">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#OSTODO">O.S. na Fila </a>
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
											<th>Data Criação</th>
											<th class="text-center">Detalhar O.S.</th>
											<th class="text-center">Iniciar Tarefa</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="row in screenData.ordensDeServicoTODO"
											ng-click=""
											ng-show="row.idFuncionario === screenData.dadosPadraoVO.funcionario.id">
											<td scope="row">{{row.idOrdemServico}}</td>
											<td>{{row.relato}}</td>
											<td>{{row.observacao}}</td>
											<td>{{row.statusOrdemServico}}</td>
											<td>{{row.dataCriacao}}</td>
											<td class="text-center"><span
												class="glyphicon glyphicon-plus text-primary clickable"
												ng-click="detalharTarefa(row, 1)"></span></td>
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
		<div class="container" ng-show="showListaTarefas">
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
											<th>Data Criação</th>
											<th class="text-center">Detalhar O.S.</th>
										</tr>
									</thead>
									<tbody>
										<tr class="clickable"
											ng-repeat="row in screenData.ordensDeServicoWIP" ng-click=""
											ng-show="row.idFuncionario === screenData.dadosPadraoVO.funcionario.id">
											<td scope="row">{{row.idOrdemServico}}</td>
											<td>{{row.relato}}</td>
											<td>{{row.observacao}}</td>
											<td>{{row.statusOrdemServico}}</td>
											<td>{{row.dataCriacao}}</td>
											<td class="text-center"><span
												class="glyphicon glyphicon-plus text-primary clickable"
												ng-click="detalharTarefa(row, 2)"></span></td>
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
		<div class="container" ng-show="showListaTarefas">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#OSDONE">O.S. Finalizadas </a>
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
											<th>Data Criação</th>
										</tr>
									</thead>
									<tbody>
										<tr class="clickable"
											ng-repeat="row in screenData.ordensDeServicoDONE" ng-click=""
											ng-show="row.idFuncionario === screenData.dadosPadraoVO.funcionario.id">
											<td scope="row">{{row.id_orcamento}}</td>
											<td>{{row.relato}}</td>
											<td>{{row.statusOrdemServico}}</td>
											<td>{{row.dataCriacao}}</td>
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


		<!-- INICIO FORMULARIO DETALHE ORDEM SERVICO -->
		<div ng-show="showDetalheTarefa">
			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Detalhe Ordem de Servico</div>
				<div class="panel-body">

					<div class="col-sm-1 form-group">
						<label>Código</label> <input type="text" class="form-control"
							name="equipamento"
							value="{{screenData.ordemDeServicoVO.idOrdemServico}}" disabled>
					</div>

					<div class="col-sm-3 form-group">
						<label>Data Criação</label> <input type="text"
							class="form-control" name="equipamento"
							value="{{screenData.ordemDeServicoVO.dataCriacao}}" disabled>
					</div>

					<div class="col-sm-8 form-group">
						<label>Nome Cliente</label> <input type="text"
							class="form-control" name="nome"
							value="{{screenData.cliente.nome}}" disabled>
					</div>

					<div class="col-sm-3 form-group">
						<label>Especialidade</label> <input type="text"
							class="form-control" name="especialidade"
							value="{{screenData.ordemDeServicoVO.especialidade}}" disabled>
					</div>

					<div class="col-sm-3 form-group">
						<label>Modelo Equipamento</label> <input type="text"
							class="form-control" name="equipamento"
							value="{{screenData.equipamento.modelo}}" disabled>
					</div>

					<div class="col-sm-3 form-group">
						<label>Marca Equipamento</label> <input type="text"
							class="form-control" name="equipamento"
							value="{{screenData.equipamento.marca}}" disabled>
					</div>

					<div class="col-sm-3 form-group">
						<label>Número Série Equipamento</label> <input type="text"
							class="form-control" name="equipamento"
							value="{{screenData.equipamento.numeroSerie}}" disabled>
					</div>



					<div class="col-sm-2 form-group input">
						<label>Pontuação</label> <input type="text" class="form-control"
							value="{{screenData.ordemDeServicoVO.pontos}}" disabled>
					</div>


					<div class="col-sm-5 form-group">
						<label>Relato</label>
						<textarea rows="6" class="form-control" name="relato"
							ng-model="screenData.ordemDeServicoVO.relato" disabled></textarea>
					</div>

					<div class="col-sm-5 form-group">
						<label>Observações</label>
						<textarea rows="6" placeholder="OBSERVAÇÕES" class="form-control"
							name="observacao"
							ng-model="screenData.ordemDeServicoVO.observacao"></textarea>
					</div>

				</div>

				<div class="panel-body scrollable">

					<table class="table">
						<thead>
							<tr>
								<th>Descrição</th>
								<th>Quantidade</th>
							</tr>
						</thead>
						<tbody>
							<tr class="clickable"
								ng-repeat="row in screenData.itensOrdemDeServico"
								ng-click="detalharOrcamento(row)">
								<td scope="row">{{row.descricao}}</td>
								<td>{{row.quantidade}}</td>
						</tbody>
					</table>
				</div>
				

			</div>

			<!-- FIM FORMULARIO DETALHE  ORDEM SERVICO-->

				<div class="col-sm-12 form-group text-right" ng-cloak>
						<input type="button" class="btn btn-default bt"
							ng-click="voltar()" value="Voltar">
							
							<input type="button" ng-show="iniciarTarefa" class="btn btn-primary bt"
							ng-click="iniciarTarefaByID()" value="Iniciar Tarefa">
							
							<input type="button" ng-show="finalizarTarefa" class="btn btn-primary bt"
							ng-click="finalizarTarefa()" value="Finalizar Tarefa">
					</div>



		</div>




		<%@ include file="../rodape.jsp"%>