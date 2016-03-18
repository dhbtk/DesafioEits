Stockontrol.controller('UsersController',function($scope, $mdToast, $mdSidenav, $mdDialog)
{
	/** Localização do cabeçalho **/
	$scope.header.location = '/resources/views/users/users-header.html';
	/*
	 * Modelo
	 */
	$scope.model = {
		filters: {
			text: null,
			active: null,
			profile: null,
		},
		profileOptions: [
			{name: "Usuário", value: "User"},
			{name: "Administrador", value: "Administrator"}
		],
		user: null,
		request: {
			content: [],
		},
		page: 1,
		pageSize: 10,
		// Para exibir/esconder a barrinha de progresso indeterminado
		tasks: 0,
	};

	/*
	 * Métodos
	 */

	/**
	 *
	 * Listagem
	 *
	 */

	// Busca os usuários filtrados lá no serviço
	$scope.fetchUsers = function(text, active, profile, page, limit, sortOrder, sortProp)
	{
		// Deixando em 'loading'
		$scope.model.tasks++;
		// Filtros
		text = text || $scope.model.filters.text;
		active = active || $scope.model.filters.active;
		profile = profile || $scope.model.filters.profile;
		// Paginação
		pageRequest = new SimplePageRequest();
		pageRequest.page = (page || $scope.model.page) - 1;
		pageRequest.size = limit || $scope.model.pageSize;
		// Ordem
		pageRequest.property = sortProp || 'id';
		pageRequest.direction = sortOrder || 'ASC';
		//pageRequest.sort = { orders: [{ direction: 'ASC', property: 'id', nullHandlingHint: null}]};
		userService.listAllByFilters(text, active, profile, pageRequest,{
			callback:function(data)
			{
				$scope.model.tasks--;
				$scope.model.request = data;
				$scope.$apply();
			},
			errorHandler: function()
			{
				$scope.model.tasks--;
				$scope.$apply();
			},
			timeout: 1000,
		});
	};

	$scope.paginateTable = function(page, limit)
	{
		console.log('Pedindo página ' + page + ', mostrando ' + limit + ' por página');
		$scope.fetchUsers(null, null, null, page, limit);
	};

	// Chama $scope.fetchUsers() caso a tecla seja um enter
	$scope.handleSearchBoxKeyPress = function($event)
	{
		if($event.which === 13)
		{
			$scope.fetchUsers();
		}
	};

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

	// Buscando a lista inicial de usuários
	$scope.fetchUsers();

	// Atualizando a lista de usuários conforme os filtros
	$scope.$watch('model.filters', function(){ $scope.fetchUsers(); }, true);
});
