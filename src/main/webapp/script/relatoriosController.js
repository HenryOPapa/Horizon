app.controller('RelatoriosController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'relatorios/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}
	
	$scope.atualizarFiltro = function(){
			
		var params = {
				'idFuncionario' : $scope.formData.funcionario,
		}
			
			$http.post($scope.path + 'atualizarFiltroFuncionario?&idTarefa='+$scope.tarefa, params).then(
					function(response) {
						if (response.status === 200) {
							
							
							
						}
					}
			);
			
		}

	
}]);