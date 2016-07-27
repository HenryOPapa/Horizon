 app.controller('LoginController', 
		 function($scope, $http) {
	 window.scope = $scope;
		$scope.screenData = {};
		$scope.formData = {};
		
		
	 $scope.init = function() {
		 $scope.screenData = CONSTANTS.RETORNO_JSTL;
			
		}
	 
	 
	 
	 
});