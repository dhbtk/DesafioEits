Stockontrol.controller('EditController', function($controller, $scope, $mdSidenav)
{
	$controller('FormController', {$scope: $scope});

	$scope.cancel = function()
	{
		$mdSidenav('rightPanel').close();
	};
});
