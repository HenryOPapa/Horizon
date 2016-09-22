app.controller('ManutencaoOrcamentoController', ['$scope', '$http', function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'manutencaoOrcamento/';
	$scope.showListOrcamento = true;
	$scope.showDetalheOrcamento = false;
	$scope.showListPecaServico = false;
	$scope.screenData.itensPecaServico = [];
	
	
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
		for(var i=0; i < $scope.screenData.itensPecaServico.length; i++){
			$scope.valorFinal += Number($scope.screenData.itensPecaServico[i].valorFinal);
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

	$scope.adicionarPeca = function() {
		var params = {
				'idPeca': $scope.formData.peca,
		}

		$http.post($scope.path + 'adicionarServico', params).then(
				function(response) {
					if (response.status == 200) {
						$scope.showListPecaServico = true;
						var itemDeServico = response.data.itemDeServico;

						if($scope.screenData.itensPecaServico == undefined){
							$scope.screenData.itensPecaServico = response.data.itensPecaServico;
							$scope.screenData.itensPecaServico.push(itemDeServico);		

						}else{
							$scope.screenData.itensPecaServico.push(itemDeServico);							

						}
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
						var pecaUtilizada = response.data.pecaUtilizada;

						if($scope.screenData.listPecasUtilizadas == undefined){
							$scope.screenData.listPecasUtilizadas = response.data.listPecasUtilizadas;
							$scope.screenData.listPecasUtilizadas.push(pecaUtilizada);		

						}else{
							$scope.screenData.listPecasUtilizadas.push(pecaUtilizada);							

						}
					}	

				}

		);
	}
	$scope.limparLista = function (){
		$scope.screenData.listPecasUtilizadas = undefined;
		atualizaLista();
	}
	
	$scope.deletarItemLista = function (index) {
		$scope.screenData.listPecasUtilizadas.splice(index, 1);
		atualizaLista();
		
	}





}]);