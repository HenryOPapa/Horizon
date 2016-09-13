<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="PecaController" ng-init="init()">
		<form>

			<div class="panel panel-default">
				<div class="panel-heading accordion-heading accordion-heading-sm">
					Nova Peça</div>
				<div class="panel-body">

					<div class="col-sm-3 form-group">
						<strong>Descrição: </strong>
					</div>

					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="descricao"
							ng-model="formData.descricao">
					</div>

					<div class="col-sm-3 form-group">
						<strong>Valor: </strong>
					</div>

					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="valor"
							ng-model="formData.valor">
					</div>

					<div class="col-sm-3 form-group">
						<strong>Quantidade Mínima: </strong>
					</div>

					<div class="col-sm-3 form-group">
						<input type="text" class="form-control" name="quantidadeMinima"
							ng-model="formData.quantidadeMinima">
					</div>

					<div class="col-sm-12 form-group text-left" ng-cloak>
						<input type="button" class="btn btn-default bt"
							ng-click="adicionaPeca()" value="Confirmar">
					</div>

				</div>
			</div>

		</form>

		<div id="cadastroSucesso"
			class="col-sm-12 form-group text-left alert alert-success" ng-cloak>
			Peça cadastrada com sucesso.</div>

		<div id="cadastroErro"
			class="col-sm-12 form-group text-left alert alert-danger" ng-cloak>
			Erro ao cadastrar a peça.</div>


	</div>

	<%@ include file="../rodape.jsp"%>