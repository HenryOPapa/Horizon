
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<div class="container" ng-controller="ManutencaoOrcamentoController"
	ng-init="init()">

	<!-- INICIO PAINEL DE ORCAMENTOS -->
	<div ng-show="showListOrcamento">
		<div class="input-group">
			<div class="input-group-addon">
				<i class="glyphicon glyphicon-search"></i>
			</div>
			<input type="text" class="form-control" ng-model="valorFiltro"
				placeholder="Procurar" id="pesquisa">
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
							ng-click="detalharOrcamento(row)"
							ng-show="row.statusOrcamento == 'EM_ABERTO'">
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


	<div id="erroDetalharOrcamento"
		class="col-sm-12 form-group text-left alert alert-danger" ng-cloak>
		Oops, ocorreu algum erro</div>

	<!-- INICIO FORMULARIO DETALHE ORCAMENTO -->
	<div ng-show="showDetalheOrcamento">
		<div class="panel panel-default">
			<div class="panel-heading accordion-heading accordion-heading-sm">
				Detalhes Orçamento</div>
			<div class="panel-body">

				<div class="col-sm-6 form-group">
					<label>Cliente</label> <input type="text" class="form-control"
						name="nome" value="{{screenData.cliente.nome}}" disabled>
				</div>

				<div class="col-sm-3 form-group">
					<label>CPF</label> <input type="text" class="form-control"
						name="cpf" value="{{screenData.cliente.cpf}}" disabled>
				</div>

				<div class="col-sm-3 form-group">
					<label>Especialidade</label> <input type="text"
						class="form-control" name="especialidade"
						value="{{screenData.especialidade.descricao}}" disabled>
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

				<div class="col-sm-3 form-group input">
					<label>Pontuação</label> <select class="form-control"
						ng-model="formData.orcamento.pontos">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="5">5</option>
						<option value="8">8</option>
						<option value="13">13</option>
						<option value="20">20</option>
						<option value="40">40</option>
						<option value="100">100</option>

					</select>
				</div>




				<div class="col-sm-6 form-group">
					<label>Relato</label>
					<textarea rows="6" class="form-control" name="relato"
						ng-model="formData.orcamento.relato" disabled></textarea>
				</div>

				<div class="col-sm-6 form-group">
					<label>Observações</label>
					<textarea rows="6" placeholder="OBSERVAÇÕES" class="form-control"
						name="observacao" ng-model="formData.orcamento.observacao"></textarea>
				</div>



				<!-- Adicionar Servicos -->
				<div class="col-sm-4 form-group input">
					<select class="form-control" ng-model="formData.peca">
						<option value="">Nova Peça</option>
						<option ng-repeat="item in screenData.pecas" value="{{item}}">{{item.descricao}}</option>
					</select>
				</div>

				<div class="col-sm-2 form-group" ng-cloak>
					<input type="button" class="btn btn-default bt"
						ng-click="adicionarItem(1)" value="Adicionar">
				</div>


				<!-- FIM Adicionar Pecas -->

				<!-- Adicionar Pecas -->
				<div class="col-sm-4 form-group input">
					<select class="form-control" ng-model="formData.servico">
						<option value="">Novo Serviço</option>
						<option ng-repeat="item in screenData.servicos"
							value="{{item}}">{{item.descricao}}</option>
					</select>
				</div>



				<div class="col-sm-2 form-group" ng-cloak>
					<input type="button" class="btn btn-default bt"
						ng-click="adicionarItem(2)" value="Adicionar">
				</div>
				<!-- FIM Adicionar Servicos -->






			</div>
		</div>

		<!-- FIM FORMULARIO DETALHE ORCAMENTO -->
		<!-- LISTA PECAS E SERVICOS -->
		<div ng-show="showListPecaServico">


			<div class="panel-body">

				<table class="table">
					<tbody>
						<tr class="clickable" ng-repeat="row in screenData.itensDeServico">
							<td scope="row">{{row.descricao}}</td>
							<td><input type="number" ng-model="row.quantidade"
								ng-change="calcula(row)" placeholder="Quantidade"></td>
							<td value="valorTotal">R$ {{row.valor *
								row.quantidade}}</td>
							<td class="text-center"><span
								class="glyphicon glyphicon-trash text-danger clickable"
								ng-click="deletarItemLista($index)"></span></td>
						</tr>

					</tbody>
				</table>
				<div class="col-sm-10 text-right">Total: R$ {{valorTotal}}</div>

			</div>
			<div class="col-sm-12 form-group text-rigth" ng-cloak>
				<input type="button" class="btn btn-danger bt"
					ng-click="limparLista()" value="Limpar">
				<input type="button" class="btn btn-default bt"
					ng-click="finalizar()" value="Finalizar">
			</div>

		</div>
			<!--FIM LISTA PECAS E SERVICOS -->

		<!--Mensagem de Alert -->
		<div id="sucessoSalvarAlteracoes" class="alert alert-success">
			<strong>Sucesso ao salvar alterações.</strong>
		</div>
		<!--End Mensagem de Alert -->


		<!--Mensagem de Alert -->
		<div id="erroSalvarAlteracoes" class="alert alert-danger">
			<strong>Erro ao salvar alterações.</strong>
		</div>
		<!--End Mensagem de Alert -->

		<!--Mensagem de Alert -->
		<div id="sucessoAtualizaOrcamento" class="alert alert-success">
			<strong>Sucesso ao atualizar orcamento</strong>
		</div>
		<!--End Mensagem de Alert -->


		<!--Mensagem de Alert -->
		<div id="erroAtualizaOrcamento" class="alert alert-danger">
			<strong>Erro ao autalizar Orcamento</strong>
		</div>
		<!--End Mensagem de Alert -->



	</div>
	<!-- INICIO FORMULARIO DETALHE ORCAMENTO <div ng-show="showDetalheOrcamento"> -->



</div>
<!-- <div class="container" ng-controller="ManutencaoOrcamentoController"
		ng-init="init()"> -->


<%@ include file="../rodape.jsp"%>