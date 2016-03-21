Stockontrol.controller('NewUserController', function($controller, $scope)
{
	$controller('NewController', {$scope: $scope});
	$scope.user = new User();
	$scope.user.active = true;
	$scope.entity = $scope.user;
	$scope.saveFunction = userService.insert;
	$scope.successMessage = function(user)
	{
		return 'Usuário "' + user.email + '" criado';
	};
});
