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
		if($scope.formData.login == undefined){
			alertLogin.style.display = "block";
			setTimeout(function(){
				alertLogin.style.display = "none";
			},5000);
		}
		if($scope.formData.senha == undefined){
			alertSenha.style.display = "block";
			setTimeout(function(){
				alertSenha.style.display = "none";
			},5000);
		}

		$http.post($scope.path + 'validarAcesso', params).then(
				function(response) {
					if (response.status === 200) {
						$scope.screenData.usuario = response.data;
						window.location.href = "http://localhost:8080/horizon/menu";					
					}else{
						alertInvalido.style.display = "block";
						setTimeout(function(){
							alertInvalido.style.display = "none";
						},5000);
						

					}
				}
		);
	}




}]);