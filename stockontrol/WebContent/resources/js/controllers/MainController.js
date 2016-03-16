Stockontrol.controller('MainController',function($scope, $http, $state, $window)
{
	/*
	 * Modelo
	 */
	$scope.model = {
		currentUser: null
	}

	/*
	 * Métodos
	 */

	$scope.openLogoutMenu = function($mdOpenMenu, $event)
	{
		$mdOpenMenu($event);
	}
	$scope.logout = function()
	{
		$window.location.href = "/logout";
	}

	/*
	 * Inicialização
	 */

	userService.getCurrent(function(data)
	{
		$scope.model.currentUser = data;
	});
});
