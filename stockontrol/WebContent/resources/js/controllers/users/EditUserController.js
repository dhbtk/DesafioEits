Stockontrol.controller('EditUserController', function($controller, $scope)
{
	$controller('EditController', {$scope: $scope});
	$scope.entity = $scope.user;
	$scope.saveFunction = userService.save;
	$scope.successMessage = function(user)
	{
		return 'Usuário "' + user.email + '" alterado';
	};
});
