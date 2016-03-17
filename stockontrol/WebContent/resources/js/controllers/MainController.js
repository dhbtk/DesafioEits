Stockontrol.controller('MainController',function($scope, $http, $state, $window)
{
	/*
	 * Modelo
	 */
	$scope.model = {}

	/** Mantém a URL da parcial do cabeçalho **/
	$scope.header = {};

	/** Usuário atual **/

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
		$scope.currentUser = data;
		$scope.currentUser.admin = function()
		{
			return this.profile == 'Administrator';
		}
	});
});
