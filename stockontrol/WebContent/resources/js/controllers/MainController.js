Stockontrol.controller('MainController',function($scope, $mdSidenav, $http, $state, $window, $timeout)
{
	// defaults do DWR
	dwr.engine.setTextHtmlHandler(function()
	{
		$window.location.href = "/";
	});

	dwr.engine.setPreHook(function()
	{
		$timeout(function()
		{
			$scope.tasks++;
		});
	});

	dwr.engine.setPostHook(function()
	{
		$timeout(function()
		{
			$scope.tasks--;
		});
	});

	/*
	 * Modelo
	 */
	$scope.model = {};
	$scope.tasks = 0;

	/** Mantém a URL da parcial do cabeçalho **/
	$scope.header = {};

	/** Usuário atual **/
	$scope.currentUser = null;

	/*
	 * Métodos
	 */

	$scope.openLogoutMenu = function($mdOpenMenu, $event)
	{
		$mdOpenMenu($event);
	};

	$scope.logout = function()
	{
		$window.location.href = "/logout";
	};

	$scope.openMenu = function()
	{
		$mdSidenav('leftMenu').open();
	};

	$scope.goTo = function(where)
	{
		$mdSidenav('leftMenu').close().then(function()
		{
			$state.go(where);
		});
	};

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
