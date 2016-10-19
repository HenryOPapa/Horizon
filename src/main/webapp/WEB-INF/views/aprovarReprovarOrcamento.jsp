
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>

	<div class="container"
		ng-controller="AprovarReprovarOrcamentoController">

		<!-- 
		INICIO DO PAINEL DE LISTA ORCAMENTO -->
		<div ng-init="init()">
			<div ng-show="showListOrcamento">
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
						Orcamentos</div>

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
								<tr class="clickable"
									ng-repeat="row in screenData.orcamentos | filter:valorFiltro"
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
		</div>
		<!-- 
		FIM DO PAINEL DE LISTA ORCAMENTO -->

		<div ng-show="showOrcamento">
			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Detalhe Orçamento</div>
				<div class="panel-body">

					<div class="col-sm-6 form-group">
						<label>Cliente</label> <input type="text" class="form-control"
							name="nome" value="{{screenData.cliente.nome}}" disabled>
					</div>

					<div class="col-sm-6 form-group">
						<label>CPF</label> <input type="text" class="form-control"
							name="cpf" value="{{screenData.cliente.cpf}}" disabled>
					</div>

					<div class="col-sm-4 form-group">
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

					<div class="col-sm-6 form-group">
						<label>Relato</label>
						<textarea rows="6" class="form-control" name="relato"
							ng-model="screenData.orcamento.relato" disabled></textarea>
					</div>

					<div class="col-sm-6 form-group">
						<label>Observações</label>
						<textarea rows="6" placeholder="OBSERVAÇÕES" class="form-control"
							name="observacao" ng-model="screenData.orcamento.observacao"></textarea>
					</div>

				</div>

			</div>
			<div class="panel-body scrollable">

				<table class="table">
					<thead>
						<tr>
							<th>Descrição</th>
							<th>Quantidade</th>
							<th>Valor</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
						<tr class="clickable"
							ng-repeat="row in screenData.itensDeServico | filter:valorFiltro"
							ng-click="detalharOrcamento(row)">
							<td scope="row">{{row.descricao}}</td>
							<td>{{row.quantidade}}</td>
							<td>R$ {{row.valor}}</td>
							<td>R$ {{row.quantidade * row.valor}}</td>
					</tbody>
					</tr>
					<tr class="hightrow col-sm-12">
						<td><strong class="text-primary">Valor Total
								Orçamento</strong></td>
						<td class="text-right"><strong class="text-primary">R$
								{{screenData.orcamento.valorTotal}}</strong></td>
					</tr>
				</table>
			</div>


			<div class="col-sm-12 form-group text-right" ng-cloak>
				<input type="button" class="btn btn-primary bt"
					ng-click="aprovarOrcamento()" value="Aprovar"> <input
					type="button" class="btn btn-danger bt"
					ng-click="reprovarOrcamento()" value="Reprovar">
					<input
					type="button" class="btn btn-default bt"
					ng-click="cancelar()" value="Cancelar">
			</div>
		</div>

	</div>

	<%@ include file="../rodape.jsp"%>