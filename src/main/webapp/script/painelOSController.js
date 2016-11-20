app.controller('PainelOSController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'painelOS/';


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
	
	
	
	 
	


}]);