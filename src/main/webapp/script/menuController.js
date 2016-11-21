app.controller('MenuController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'menu/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}
	
	$scope.logout = function() {

		$http.post($scope.path + 'logout').then(
				function(response) {
					if (response.status === 200) {
						window.location.href = "http://localhost:8080/horizon/login";
					}
				}
		);
	}
	
	$scope.alterarSenha = function(){
		window.location.href = "http://localhost:8080/horizon/alterarSenha";

	}
	



}]);