app.controller('RelatoriosController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'relatorios/';
	$scope.showListOrcamentos = false;
	$scope.showListOrdemServico = false;

	$scope.init = function() {
		$scope.screenData = RETORNO;

	}
	
	$scope.atualizarFiltro = function(){
			
		var params = {
				'id' : $scope.formData.funcionario,
		}
			
			$http.post($scope.path + 'atualizarFiltroFuncionario?&idTarefa='+$scope.tarefa, params).then(
					function(response) {
						if (response.status === 200) {
							if(response.data.x == 1){
								$scope.showListOrcamentos = true;
								$scope.showListOrdemServico = false;
							}else if(response.data.x == 2){
								$scope.showListOrdemServico = true;
								$scope.showListOrcamentos = false;
							}
							$scope.screenData.resultadoPesquisa = response.data.resultadoPesquisa;
							
						}
					}
			);
			
		}

	
}]);