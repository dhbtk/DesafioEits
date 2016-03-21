Stockontrol.controller('EditController', function($controller, $scope, $mdSidenav, $mdToast)
{
	$controller('FormController', {$scope: $scope});

	$scope.cancel = function()
	{
		$mdSidenav('rightPanel').close();
	};
});
