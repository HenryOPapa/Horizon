app.controller('LoginController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'login/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}

	$scope.validaLogin = function() {
		var params = {
				'login': $scope.formData.login,
				'senha' :$scope.formData.senha,
		}

		$http.post($scope.path + 'validarAcesso', params).then(
				function(response) {
					if (response.status === 200) {
						$scope.screenData.usuario = response.data;
						window.location.href = "http://localhost:8080/horizon/menu";					
					}else{
						window.location.href = "http://localhost:8080/horizon/login";

					}
				}
		);
	}




}]);