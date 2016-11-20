app.controller('PainelOSController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.showListaTarefas = true;
	$scope.showDetalheTarefa = false;
	$scope.path = 'painelOS/';
	$scope.iniciarTarefa = false;
	$scope.finalizarTarefa = false;




	$scope.init = function() {
		$scope.screenData = RETORNO;

	}
	
	$scope.iniciarTarefa = function(row){
		var params = {
				'idOrdemServico' : row.idOrdemServico,
		}
		$http.post($scope.path + 'iniciarTarefa', params).then(
				function(response) {
					if (response.status == 200) {
						location.reload();
						
					}
				}
		);
	}
	
	$scope.iniciarTarefaByID = function(){
		var params = {
				'idOrdemServico' : $scope.screenData.ordemDeServicoVO.idOrdemServico,
		}
		$http.post($scope.path + 'iniciarTarefa', params).then(
				function(response) {
					if (response.status == 200) {
						location.reload();
						
					}
				}
		);
	}
	
	$scope.finalizarTarefa = function(){
		var params = {
				'idOrdemServico' : $scope.screenData.ordemDeServicoVO.idOrdemServico,
		}
		$http.post($scope.path + 'finalizarTarefa', params).then(
				function(response) {
					if (response.status == 200) {
						location.reload();
						
					}
				}
		);
	}
	
	
	$scope.detalharTarefa = function(row,x){
		var params = {
				'idOrdemServico' : row.idOrdemServico,
		}
		$http.post($scope.path + 'detalharTarefa', params).then(
				function(response) {
					if (response.status == 200) {
						$scope.iniciarTarefa = false;
						$scope.finalizarTarefa = false;

						$scope.showListaTarefas = false;
						$scope.showDetalheTarefa = true;
						$scope.screenData.cliente = response.data.cliente,
						$scope.screenData.equipamento = response.data.equipamento,
						$scope.screenData.ordemDeServicoVO = response.data.ordemDeServicoVO;
						$scope.screenData.itensOrdemDeServico = response.data.itensOrdemDeServico;
						
						if(x == 1){
							$scope.iniciarTarefa = true;
						}else if(x == 2){
							$scope.finalizarTarefa = true;
						}
					}
				}
		);
	}
	
	$scope.voltar = function(){
		$scope.showListaTarefas = true;
		$scope.showDetalheTarefa = false;
	}
	
	
	
	 
	


}]);