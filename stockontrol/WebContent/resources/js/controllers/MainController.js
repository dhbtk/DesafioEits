Stockontrol.controller('MainController',function($scope, $http, $state, $window)
{
	$scope.openLogoutMenu = function($mdOpenMenu, $event)
	{
		$mdOpenMenu($event);
	}
	$scope.logout = function()
	{
		$window.location.href = "/logout";
	}
});
