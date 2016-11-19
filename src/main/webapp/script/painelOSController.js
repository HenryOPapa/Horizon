app.controller('PainelOSController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'painelOS/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}
	
	$scope.iniciarTarefa = function(){
		
	}
	
	
	


}]);