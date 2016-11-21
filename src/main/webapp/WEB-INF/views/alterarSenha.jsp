<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="AlterarSenhaController" ng-init="init()">


		<div class="panel panel-default">
			<div class="panel-heading accordion-heading accordion-heading-sm">
				Alterar Senha</div>
			<div class="panel-body">


				<div class="col-sm-3 form-group">
					<input type="text" class="form-control" name="notaFiscal"
						ng-model="formData.senha" placeholder="Nova senha">
				</div>

				<div class="col-sm-3 form-group">
					<input type="text" class="form-control" name="notaFiscal"
						ng-model="formData.senhaAgain" placeholder="Nova senha novamente">
				</div>

				<div class="col-sm-12 form-group text-left" ng-cloak>
					<input type="button"class="btn btn-default bt"
						ng-click="atualizarSenha()" value="Alterar">
				</div>

			</div>
		</div>

		<div id="senhaAlteradaSucesso"
			class="col-sm-12 form-group text-left alert alert-success" ng-cloak>
			Senha alterada com sucesso.</div>

		<div id="senhaAlteradaErro"
			class="col-sm-12 form-group text-left alert alert-danger" ng-cloak>
			Ops, algo deu errado, verifique as senhas e tente novamente.</div>
		<!-- PAINEL ESTOQUE -->

	</div>
	<!-- 
		FIM DO PAINEL DE ESTOQUE-->




	<%@ include file="../rodape.jsp"%>