Stockontrol.controller('MainController',function($scope, $mdSidenav, $http, $state, $window, $timeout, identityService, $rootScope)
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
	$scope.isAdmin = false;

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
			identityService.authenticate(null);
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
				$scope.isAdmin = identityService.admin();
			}
			else
			{
				// JUSTIFICATIVA: devido a condições de corrida de vez em quando o
				// currentUser() ainda vai ser nulo, então checamos de novo depois de um
				// tempinho
				identityService.currentUser().then(updateUserEmail);
			}
		}, 500);
	}

	identityService.currentUser().then(updateUserEmail);
});
