Stockontrol.controller('UsersController',function($scope, $http, $mdToast, $mdSidenav, $window)
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
	}

	/*
	 * Métodos
	 */

	/**
	 *
	 * Listagem
	 *
	 */

	// Busca os usuários filtrados lá no serviço
	$scope.fetchUsers = function(text, active, profile, page, limit)
	{
		$scope.model.tasks++;
		text = text || $scope.model.filters.text;
		active = active || $scope.model.filters.active;
		profile = profile || $scope.model.filters.profile;
		pageRequest = new PageRequest();
		pageRequest.page = (page || $scope.model.page) - 1;
		pageRequest.size = limit || $scope.model.pageSize;
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
	}

	$scope.paginateTable = function(page, limit)
	{
		console.log('Pedindo página ' + page + ', mostrando ' + limit + ' por página');
		$scope.fetchUsers(null, null, null, page, limit);
	}

	// Chama $scope.fetchUsers() caso a tecla seja um enter
	$scope.handleSearchBoxKeyPress = function($event)
	{
		if($event.which === 13)
		{
			$scope.fetchUsers();
		}
	};

	/**
	 *
	 * Edição
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
	}

	/*
	 * Inicialização
	 */

	// Buscando a lista inicial de usuários
	$scope.fetchUsers();

	// Atualizando a lista de usuários conforme os filtros
	$scope.$watch('model.filters', function(){ $scope.fetchUsers(); }, true);
});

Stockontrol.controller('EditUserController', function($scope)
{

});

Stockontrol.controller('NewUserController', function($scope)
{
	$scope.user = new User();
	$scope.user.active = true;
});
