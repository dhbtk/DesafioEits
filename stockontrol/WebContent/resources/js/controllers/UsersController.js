Stockontrol.controller('UsersController',function($controller, $scope, $mdToast, $mdSidenav, $mdDialog)
{
	// Herdando do controller base
	$controller('BaseController', {$scope: $scope});

	/** Localização do cabeçalho **/
	$scope.header.location = '/resources/views/users/users-header.html';
	/*
	 * Modelo
	 */
	angular.extend($scope.model, {
		filters: ['text','active','profile'],
		filterData: {
			text: null,
			active: null,
			profile: null,
		},
		profileOptions: [
			{name: "Usuário", value: "User"},
			{name: "Administrador", value: "Administrator"}
		],
		user: null,
		order: 'fullName',
	});

	/*
	 * Métodos
	 */

	/**
	 * Novo usuário
	 */

	$scope.openNewUser = function()
	{
		$mdDialog.show({
			controller: 'NewUserController',
			templateUrl: '/resources/views/users/users-new.html',
			scope: $scope.$new(),
			clickOutsideToClose: true
		});
	};

	/**
	 *
	 * Edição / Desativação / Ativação
	 *
	 */

	$scope.openEditUser = function(id)
	{
		$scope.model.tasks++;
		userService.find(id, function(user)
		{
			$scope.model.tasks--;
			$scope.user = user;
			$mdSidenav('rightPanel').open();
		});
	};

	$scope.deactivateUser = function(user)
	{
		var dialog = $mdDialog.confirm()
				.title('Desativar Usuário')
				.textContent('Desativar o usuário "' + user.email + '"?')
				.ok('Desativar')
				.cancel('Cancelar');
		$mdDialog.show(dialog).then(function()
		{
			$scope.model.tasks++;
			userService.find(user.id, function(user)
			{
				userService.deactivate(user, function(user)
				{
					$scope.model.tasks--;
					$mdToast.show(
							$mdToast.simple()
									.textContent('Usuário "' + user.email + '" desativado')
									.position('bottom')
									.hideDelay(3000));
					$scope.fetchUsers();
				});
			});
		});
	};

	$scope.activateUser = function(user)
	{
		var dialog = $mdDialog.confirm()
				.title('Ativar Usuário')
				.textContent('Ativar o usuário "' + user.email + '"?')
				.ok('Ativar')
				.cancel('Cancelar');
		$mdDialog.show(dialog).then(function()
		{
			$scope.model.tasks++;
			userService.find(user.id, function(user)
			{
				userService.activate(user, function(user)
				{
					$scope.model.tasks--;
					$mdToast.show(
							$mdToast.simple()
									.textContent('Usuário "' + user.email + '" ativado')
									.position('bottom')
									.hideDelay(3000));
					$scope.fetchUsers();
				});
			});
		});
	};

	/*
	 * Inicialização
	 */

	// Definindo nossa função de filtragem
	$scope.fetchFunction = userService.listAllByFilters;
});
