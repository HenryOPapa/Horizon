app.controller('PecaController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
//	$scope.path = 'login/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}

//	$scope.adicionaPeca = function() {
//		var params = {
//				'descricao': $scope.formData.descricao,
//				'valor' :$scope.formData.valor,
//		}
//
//		$http.post($scope.path + 'cadastrarPeca', params).then(
//				function(response) {
//					if (response.status === 200) {
//						console.log('ENTROU');
//					}else{
//						console.log('NAO ENTROU');
//					}
//				}
//		);
//	}
//



}]);