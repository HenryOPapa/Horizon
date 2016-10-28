app.controller('FuncionarioController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'cadastroFuncionario/';


	
	$scope.init = function() {
		$scope.screenData = RETORNO;

	}
	
	$scope.cadastrarFuncionario = function() {
		var params = $scope.formData;
		
		$http.post($scope.path + 'cadastrarFuncionario?&nivelAcesso=' + $scope.nivelAcesso, params).then(
				function(response) {
					if (response.status == 200) {
						
						sucessoCadastro.style.display = "block";
						setTimeout(function(){
							sucessoCadastro.style.display = "none";
							location.reload();
						},5000);
						
					}else{
						erroCadastro.style.display = "block";
						setTimeout(function(){
							erroCadastro.style.display = "none";
						},5000);

					}
				}
		);
	}
	
	
	$scope.cancelar = function(){
		location.reload();
	}




}]);