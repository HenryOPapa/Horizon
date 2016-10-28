app.controller('EspecialidadeController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'especialidade/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}

	$scope.adicionaEspecialidade = function() {
		var params = {
				'descricao': $scope.formData.descricao,
		}

		$http.post($scope.path + 'cadastrarEspecialidade', params).then(
				function(response) {
					if (response.status === 200) {
						cadastroSucesso.style.display = "block";
						setTimeout(function(){
							cadastroSucesso.style.display = "none";
							location.reload();					

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