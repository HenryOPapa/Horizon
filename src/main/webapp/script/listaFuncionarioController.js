app.controller('ListaFuncionarioController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'listaFuncionarios/';


	$scope.init = function(){
		$scope.screenData = RETORNO;

	}

	$scope.cancelar = function(){
		location.reload();
	}




}]);