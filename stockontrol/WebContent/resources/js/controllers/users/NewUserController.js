Stockontrol.controller('NewUserController', function($scope, $mdDialog)
{
	$scope.form = null;
	$scope.user = new User();
	$scope.user.active = true;

	$scope.setForm = function(form)
	{
		$scope.form = form;
		console.log(form);
	};

	$scope.cancel = function()
	{
		$mdDialog.cancel();
	};

	$scope.create = function()
	{
		if($scope.form.$valid)
		{
			$scope.model.tasks++;
			userService.insert($scope.user, function(user)
			{
				$scope.model.tasks--;
				$mdDialog.cancel();
				$mdToast.show(
						$mdToast.simple()
								.textContent('Usuário "' + user.email + '" criado')
								.position('bottom')
								.hideDelay(3000));
				$scope.fetchUsers();
			});
		}
	};
});
