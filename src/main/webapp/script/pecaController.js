app.controller('PecaController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'cadastroPeca/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}

	$scope.adicionaPeca = function() {
		var params = {
				'descricao': $scope.formData.descricao,
				'valor' :$scope.formData.valor,
		}



		$http.post($scope.path + 'cadastrarPeca', params).then(
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