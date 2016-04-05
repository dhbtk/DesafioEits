Stockontrol.controller('MainController',function($scope, $mdSidenav, $http, $state, $window, $timeout, identity, $rootScope)
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
	$scope.currentUserEmail = null;

	/*
	 * Métodos
	 */

	$scope.openLogoutMenu = function($mdOpenMenu, $event)
	{
		$mdOpenMenu($event);
	};

	$scope.logout = function()
	{
		$http.get('/logout').then(function()
		{
			identity.authenticate(null);
			$rootScope.fromLogout = true;
			$state.go('login');
		});
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

	function updateUserEmail(user)
	{
		$timeout(function()
		{
			if(user != null)
			{
				$scope.currentUserEmail = user.email;
			}
			else
			{
				identity.currentUser().then(updateUserEmail);
			}
		});
	}

	identity.currentUser().then(updateUserEmail);
});
