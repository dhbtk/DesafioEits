Stockontrol.controller('EditUserController', function($scope, $mdSidenav, $mdToast)
{
	$scope.form = null;

	$scope.setForm = function(form)
	{
		$scope.form = form;
		console.log(form);
	};

	$scope.saveUser = function()
	{
		if($scope.form.$valid)
		{
			$scope.model.tasks++;
			userService.save($scope.user, function(user)
			{
				$scope.model.tasks--;
				$mdSidenav('rightPanel').close();
				$mdToast.show(
						$mdToast.simple()
								.textContent('Usuário "' + user.email + '" desativado')
								.position('bottom')
								.hideDelay(3000));
				$scope.fetchFetchData();
			});
		}
	};

	$scope.cancel = function()
	{
		$mdSidenav('rightPanel').close();
	};

});
