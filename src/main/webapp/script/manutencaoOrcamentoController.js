app.controller('ManutencaoOrcamentoController', ['$scope', '$http', function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'manutencaoOrcamento/';
	$scope.showListOrcamento = true;
	$scope.showDetalheOrcamento = false;
	$scope.showListPecaServico = false;
	$scope.screenData.itensDeServico = [];
	
	
	$scope.valorFinal = 0;

	$scope.init = function() {
		$scope.screenData = RETORNO;

	}
	
	$scope.calcula = function(row){
		row.valorFinal = Number (row.valor * row.quantidade);
		atualizaLista();
	}
	
	function atualizaLista(){
		$scope.valorFinal = 0;
		for(var i=0; i < $scope.screenData.itensDeServico.length; i++){
			$scope.valorFinal += Number($scope.screenData.itensDeServico[i].valorFinal);
		}
	}
	
	

	$scope.detalharOrcamento = function(row) {
		var params = {
				'id_orcamento': row.id_orcamento,
				'relato' : row.relato,
				'observacao' : row.observacao,
				'idCliente' : row.idCliente,
				'idEquipamento' : row.idEquipamento,
				'idEspecialidade' : row.idEspecialidade,
		}

		$http.post($scope.path + 'detalharOrcamento', params).then(
				function(response) {
					if (response.status == 200) {
						$scope.screenData.orcamento = response.data.orcamento,
						$scope.screenData.pecas = response.data.pecas,
						$scope.screenData.servicos = response.data.servicos,
						$scope.screenData.cliente = response.data.cliente,
						$scope.screenData.equipamento = response.data.equipamento,
						$scope.screenData.especialidade = response.data.especialidade
						$scope.showListOrcamento = false;
						$scope.showDetalheOrcamento = true;

					}else{
						erroDetalharOrcamento.style.display = "block";
						setTimeout(function(){
							erroDetalharOrcamento.style.display = "none";
						},5000);

					}
				}
		);
	}

	$scope.adicionarServico = function() {
		var params = {
				'id_servico': $scope.formData.servico,
		}

		$http.post($scope.path + 'adicionarServico', params).then(
				function(response) {
					if (response.status == 200) {
						$scope.showListPecaServico = true;
						var itemDeServico = response.data.itemDeServico;

						if($scope.screenData.itensDeServico == undefined){
							$scope.screenData.itensDeServico = response.data.itensDeServico;
							$scope.screenData.itensDeServico.push(itemDeServico);		

						}else{
							$scope.screenData.itensDeServico.push(itemDeServico);							

						}
					}	

				}

		);
	}
	
	$scope.adicionarPeca = function() {
		var params = {
				'idPeca': $scope.formData.peca,
		}

		$http.post($scope.path + 'adicionarPeca', params).then(
				function(response) {
					if (response.status == 200) {
						$scope.showListPecaServico = true;
						var itemDeServico = response.data.itemDeServico;

						if($scope.screenData.itensDeServico == undefined){
							$scope.screenData.itensDeServico = response.data.itensDeServico;
							$scope.screenData.itensDeServico.push(itemDeServico);		

						}else{
							$scope.screenData.itensDeServico.push(itemDeServico);							

						}
					}	

				}

		);
	}
	
	$scope.salvarOrcamento = function() {
		var params = {
				'pontos' : $scope.formData.ponto,
				'observacao' :$scope.screenData.orcamento.observacao,
		}

		$http.post($scope.path + 'salvarOrcamento',params, $scope.screenData.itensDeServico).then(
				function(response) {
					if (response.status == 200) {
						
						
					}	

				}

		);
	}
	
	
	
	$scope.limparLista = function (){
		$scope.screenData.itensDeServico = undefined;
		atualizaLista();
	}
	
	$scope.deletarItemLista = function (index) {
		$scope.screenData.itensDeServico.splice(index, 1);
		atualizaLista();
		
	}





}]);