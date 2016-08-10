<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../../cabecalho.jsp"%>
<body>

	<div class="container" ng-controller="ListaFuncionarioController">

		<!-- Painel que irá 
	listar os clientes -->
		<div ng-init="init()">
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
					Clientes</div>

				<div class="panel-body scrollable">

					<table class="table">
						<thead>
							<tr>
								<th>Nome</th>
								<th>CPF</th>
								<th>Telefone</th>
								<th>Email</th>
								<th class="text-center">Editar</th>
								<th class="text-center">Novo Equipamento</th>
								<th class="text-center">Excluir</th>

							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="row in screenData.clientes | filter:valorFiltro">
								<td scope="row">{{row.nome}}</td>
								<td>{{row.cpf}}</td>
								<td>{{row.telefone}}</td>
								<td>{{row.email}}</td>
								<td class="text-center"><span
									class="glyphicon glyphicon-pencil text-primary clickable"
									ng-click="atualizaCliente(row)"></span></td>
								<td class="text-center"><span
									class="glyphicon glyphicon-plus text-primary clickable"></span></td>
								<td class="text-center"><span
									class="glyphicon glyphicon-trash text-danger clickable"></span></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- 
		FIM DO PAINEL DE LISTA CLIENTE -->


		<!-- PAINEL PARA ALTERACAO DE DADOS -->
		<div class="panel panel-default" ng-show="showAtualizaCliente">

			<div class="panel-heading accordion-heading accordion-heading-sm">
				Atualizar Cliente</div>
			<div class="panel-body">

				<div class="col-sm-4 form-group">
					<label>Nome</label> <input type="text"
						value="{{screenData.clienteSelecionado.nome}}"
						class="form-control" disabled>
				</div>

				<div class="col-sm-3 form-group">
					<label>CPF</label> <input type="text"
						value="{{screenData.clienteSelecionado.cpf}}" class="form-control"
						disabled>
				</div>

				<div class="col-sm-3 form-group">
					<label>Data Nascimento</label> <input type="date"
						value="{{screenData.clienteSelecionado.dataNascimento}}"
						class="form-control" disabled>
				</div>

				<div class="col-sm-2 form-group">
					<label>Telefone</label> <input type="text"
						placeholder="{{screenData.clienteSelecionado.telefone}}" id="phone"
						class="form-control" ng-model="formData.telefone">
				</div>

				<div class="col-sm-3 form-group">
					<label>CEP</label> <input type="text" id="cep"
						placeholder="{{screenData.clienteSelecionado.cep}}" class="form-control"
						ng-model="formData.cep">
				</div>

				<div class="col-sm-4 form-group">
					<label>Logradouro</label> <input type="text"
						placeholder="{{screenData.clienteSelecionado.logradouro}}"
						class="form-control" ng-model="formData.logradouro">
				</div>

				<div class="col-sm-3 form-group">
					<label>Cidade</label> <input type="text"
						placeholder="{{screenData.clienteSelecionado.cidade}}"
						class="form-control" ng-model="formData.cidade">
				</div>

				<div class="col-sm-2 form-group">
					<label>Estado</label> <input type="text"
						placeholder="{{screenData.clienteSelecionado.estado}}"
						class="form-control" ng-model="formData.estado">
				</div>


				<div class="col-sm-4 form-group">
					<label>Email</label> <input type="text"
						placeholder="{{screenData.clienteSelecionado.email}}"
						class="form-control" ng-model="formData.email">
				</div>


				<div class="col-sm-12 form-group text-left" ng-cloak>
					<input type="button" class="btn btn-danger bt"
						ng-click="alterarDados()" value="Salvar">
				</div>

			</div>
		</div>

		<!-- FIM DO PAINEL PARA ALTERACAO DE DADOS -->


	</div>

	<%@ include file="../../rodape.jsp"%>