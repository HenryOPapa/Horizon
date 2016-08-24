app.controller('OrcamentoCadastroController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'novoOrcamento/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}

	$scope.localizarCliente = function() {
		var params = {
				'cpf': $scope.formData.cpf,
		}

		$http.post($scope.path + 'localizarCliente', params).then(
				function(response) {
					if (response.status === 200) {
						cadastroSucesso.style.display = "block";
						setTimeout(function(){
							cadastroSucesso.style.display = "none";
							window.location.href = "http://localhost:8080/horizon/cadastroPeca";					

						},5000);

					}else{
						cadastroErro.style.display = "block";
						setTimeout(function(){
							cadastroErro.style.display = "none";
						},5000);
					}
				}
		);
	}




}]);