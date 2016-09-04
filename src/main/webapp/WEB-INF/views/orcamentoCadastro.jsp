<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="OrcamentoCadastroController"
		ng-init="init()">

		<!-- INICIO PAINEL LOCALIZA CLIENTE -->
		<div ng-show="showLocalizaCliente">
			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Localizar Cliente</div>
				<div class="panel-body">
					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="cpf"
							ng-model="formData.cpf" placeholder="CPF CLIENTE">
					</div>
					<div class="col-sm-12 form-group text-left" ng-cloak>
						<input type="button" class="btn btn-default bt"
							ng-click="localizarCliente()" value="Localizar Cliente">
					</div>
				</div>
			</div>
		</div>


		<!-- INICIO ALERT -->
		<div id="clienteLocalizadoSucesso"
			class="col-sm-12 form-group text-left alert alert-success" ng-cloak>
			Cliente localizado com sucesso.</div>

		<div id="clienteLocalizadoErro"
			class="col-sm-12 form-group text-left alert alert-danger" ng-cloak>
			Cliente não pode ser localizado ou não esta cadastrado.</div>
		<!-- FIM ALERT -->

		<!-- FIM PAINEL LOCALIZA CLIENTE -->

		<!-- INICIO FORMULARIO ORCAMENTO -->
		<div ng-show="showFormularioOrcamento">
			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Novo Orçamento</div>
				<div class="panel-body">

					<div class="col-sm-5 form-group">
						<input type="text" class="form-control" name="nome"
							value="{{screenData.cliente.nome}}" disabled>
					</div>

					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="cpf"
							value="{{formData.cpf}}" disabled>
					</div>

					<div class="col-sm-4 form-group input">
						<select class="form-control" ng-model="formData.equipamento">
							<option value="">Selecione um Equipamento</option>
							<option ng-repeat="item in screenData.equipamentos"
								value="{{item.numeroSerie}}">{{item.marca}}</option>
						</select>
					</div>

					<div class="col-sm-6 form-group">
						<textarea rows="6" placeholder="RELATO DO PROBLEMA" class="form-control" name="relato"
							ng-model="formData.relato"></textarea>
					</div>
					
						<div class="col-sm-6 form-group">
						<textarea rows="6" placeholder="OBSERVAÇÕES" class="form-control" name="observacao"
							ng-model="formData.observacao"></textarea>
					</div>


					<div class="col-sm-6 form-group input">
						<label>Especialidade</label> <select class="form-control"
							ng-model="formData.id_especialidade">
							<option value="">Selecione uma especialidade</option>
							<option ng-repeat="item in screenData.especialidades"
								value="{{item.id_especialidade}}">{{item.descricao}}</option>
						</select>
					</div>
					
					

					<div class="col-sm-12 form-group text-left" ng-cloak>
						<input type="button" class="btn btn-default bt"
							ng-click="salvarOrcamento()" value="Salvar Orçamento">
							<input type="button" class="btn btn-danger bt"
							ng-click="limpar()" value="Limpar">
					</div>

				</div>
			</div>
		</div>


		<!-- INICIO ALERT -->
		<div id="sucessoSalvarOrcamento" class="col-sm-12 form-group text-left alert alert-success"
			ng-cloak>Orcamento cadastrado com sucesso.</div>

		<div id="erroSalvarOrcamento" class="col-sm-12 form-group text-left alert alert-success"
			ng-cloak>Erro ao cadastrar orcamento.</div>
		<!-- FIM ALERT -->


		<!-- FIM FORMULARIO ORCAMENTO -->






	</div>

	<%@ include file="../rodape.jsp"%>