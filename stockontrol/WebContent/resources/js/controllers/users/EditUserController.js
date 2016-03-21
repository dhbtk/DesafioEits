Stockontrol.controller('EditUserController', function($controller, $scope)
{
	$controller('EditController', {$scope: $scope});
	$scope.entity = 'user';
	$scope.saveFunction = userService.save;
	$scope.successMessage = function(user)
	{
		return 'Usuário "' + user.email + '" alterado';
	};
});
