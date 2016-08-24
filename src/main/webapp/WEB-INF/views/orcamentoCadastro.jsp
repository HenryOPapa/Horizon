<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="OrcamentoCadastroController" ng-init="init()">
		<form>

			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Novo Orçamento</div>
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


		</form>

	</div>

	<%@ include file="../rodape.jsp"%>