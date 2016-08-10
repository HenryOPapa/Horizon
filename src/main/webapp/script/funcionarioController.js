app.controller('FuncionarioController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'cadastroFuncionario/';



	$scope.cancelar = function(){
		location.reload();
	}




}]);