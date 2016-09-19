
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="ManutencaoOrcamentoController"
		ng-init="init()">

		<!-- INICIO PAINEL DE ORCAMENTOS -->
<div ng-show="showListOrcamento">
		<div class="input-group">
			<div class="input-group-addon">
				<i class="glyphicon glyphicon-search"></i>
			</div>
			<input type="text" class="form-control"
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
		<!-- FIM PAINEL DE ORCAMENTOS -->
	<!-- INICIO FORMULARIO DETALHE ORCAMENTO -->
		<div ng-show="showDetalheOrcamento">
			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Detalhes Orçamento</div>
				<div class="panel-body">

					<div class="col-sm-5 form-group">
						<input type="text" class="form-control" name="nome"
							value="{{screenData.cliente.nome}}" >
					</div>

					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="cpf"
							value="{{screenData.cliente.cpf}}" disabled>
					</div>
					
					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="equipamento"
							value="{{screenData.equipamento.numeroSerie}}" disabled>
					</div>

					<div class="col-sm-6 form-group">
						<textarea rows="6" placeholder="RELATO DO PROBLEMA" class="form-control" name="relato"
							ng-model="screenData.orcamento.relato"></textarea>
					</div>
					
						<div class="col-sm-6 form-group">
						<textarea rows="6" placeholder="OBSERVAÇÕES" class="form-control" name="observacao"
							ng-model="screenData.orcamento.observacao"></textarea>
					</div>
					
					<div class="col-sm-6 form-group">
						<input type="text" class="form-control" name="especialidade"
							value="{{screenData.especialidade.descricao}}" disabled>
					</div>
					
					
					<!-- Adicionar Pecas -->
					
					<div class="col-sm-4 form-group input">
						<select class="form-control" ng-model="formData.peca">
							<option value="">Nova peça de manutenção</option>
							<option ng-repeat="item in screenData.pecas"
								value="{{item.idPeca}">{{item.descricao}}</option>
						</select>
					</div>
					
					<div class="col-sm-2 form-grou" ng-cloak>
						<input type="button" class="btn btn-default bt"
							ng-click="adicionarPeca()" value="Adicionar">
					</div>
					
					<!-- FIM Adicionar Pecas -->
					

				</div>
			</div>
			
			
			
		</div>
			<!-- FIM FORMULARIO DETALHE ORCAMENTO -->
		
		
	</div>
	

	<%@ include file="../rodape.jsp"%>