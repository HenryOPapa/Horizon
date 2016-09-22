<%@ include file="../cabecalho.jsp"%>
<body>
	<div class="container" ng-controller="ItensDeServicoController"
		ng-init="init()">

		<!-- INICIO FORMULARIO CADASTRA PECA -->
		<div class="panel panel-default">
			<div class="panel-heading accordion-heading accordion-heading-sm">
				Nova Pe�a</div>
			<div class="panel-body">

				<div class="col-sm-3 form-group">
					<strong>Descri��o: </strong>
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
					<strong>Quantidade M�nima: </strong>
				</div>

				<div class="col-sm-3 form-group">
					<input type="text" class="form-control" name="quantidadeMinima"
						ng-model="formData.quantidadeMinima">
				</div>

				<div class="col-sm-12 form-group text-left" ng-cloak>
					<input type="button" class="btn btn-default bt"
						ng-click="adicionaPeca()" value="Adicionar">
				</div>

			</div>
		</div>

		<!-- FIM FORMULARIO CADASTRA PECA -->

		<div id="cadastroSucesso"
			class="col-sm-12 form-group text-left alert alert-success" ng-cloak>
			Peca cadastrada com sucesso.</div>

		<div id="cadastroErro"
			class="col-sm-12 form-group text-left alert alert-danger" ng-cloak>
			Erro ao cadastrar a pe�a.</div>


		<!-- INICIO FORMULARIO CADASTRO SERVICO -->
		<div class="panel panel-default">
			<div class="panel-heading accordion-heading accordion-heading-sm">
				Novo Servi�o</div>
			<div class="panel-body">

				<div class="col-sm-3 form-group">
					<strong>Descri��o: </strong>
				</div>

				<div class="col-sm-3 form-group">
					<input type="text" class="form-control" name="descricaoServico"
						ng-model="formData.descricaoServico">
				</div>

				<div class="col-sm-3 form-group">
					<strong>Valor: </strong>
				</div>

				<div class="col-sm-3 form-group">
					<input type="text" class="form-control" name="valorServico"
						ng-model="formData.valorServico">
				</div>

				<div class="col-sm-12 form-group text-left" ng-cloak>
					<input type="button" class="btn btn-default bt"
						ng-click="adicionaServico()" value="Adicionar">
				</div>

			</div>
		</div>

		<!-- FIM FORMULARIO CADASTRO SERVICO -->

		<div id="cadastroSucesso"
			class="col-sm-12 form-group text-left alert alert-success" ng-cloak>
			Peca cadastrada com sucesso.</div>

		<div id="cadastroErro"
			class="col-sm-12 form-group text-left alert alert-danger" ng-cloak>
			Erro ao cadastrar a pe�a.</div>


	</div>

	<%@ include file="../rodape.jsp"%>