<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../../cabecalho.jsp"%>
<body>

	<div class="container">

			<!--Mensagem de Alert -->
						<div id="sucessoCadastro" class="alert alert-success">
							<strong>Cliente adicionado com Sucesso!</strong>
						</div>
			<!--End Mensagem de Alert -->
			
			
			<!--Mensagem de Alert -->
						<div id="erroCadastro" class="alert alert-danger">
							<strong>Atenção</strong>, Erro ao cadastrar Cliente.
						</div>
			<!--End Mensagem de Alert -->
			
		<div ng-controller="ClienteController" class="panel panel-default">
		
			<div class="panel-heading accordion-heading accordion-heading-sm">
				Novo Cliente</div>
			<div class="panel-body">
	
				<div class="col-sm-4 form-group">
						<label>Nome</label> <input type="text"
							ng-model="formData.nome" class="form-control">
				</div>
				
				<div class="col-sm-3 form-group">
						<label>CPF</label> <input type="text"
							ng-model="formData.cpf" class="form-control">
				</div>
				
				
				<div class="col-sm-3 form-group">
					<label>Data Nascimento</label>
					<input type="text"
							ng-model="formData.dataNascimento" class="form-control">
				</div>
				
				<div class="col-sm-2 form-group">
						<label>Telefone</label> <input id="phone" type="text"
							ng-model="formData.telefone" class="form-control">
				</div>
				
				<div class="col-sm-3 form-group">
						<label>CEP</label> <input id="cep" type="text"
							ng-model="formData.cep" class="form-control"
							>
				</div>
				
				<div class="col-sm-4 form-group">
						<label>Logradouro</label> <input type="text"
							ng-model="formData.logradouro" class="form-control" >
				</div>
				
				<div class="col-sm-3 form-group">
						<label>Cidade</label> <input type="text"
							ng-model="formData.cidade" class="form-control" >
				</div>
				
				<div class="col-sm-2 form-group">
						<label>Estado</label> <input type="text"
							ng-model="formData.estado" class="form-control" >
				</div>
				
				
				<div class="col-sm-4 form-group">
						<label>Email</label> <input type="text"
							ng-model="formData.email" class="form-control" >
				</div>
				
	
				<div class="col-sm-12 form-group text-left" ng-cloak>
					<input type="button" class="btn btn-danger bt"
						ng-click="cadastrarCliente()" value="Cadastrar">
					<input type="button" class="btn btn-default bt"
						ng-click="cancelar()" value="Cancelar">
				</div>
	
			</div>
		</div>
	</div>


	<%@ include file="../../rodape.jsp"%>