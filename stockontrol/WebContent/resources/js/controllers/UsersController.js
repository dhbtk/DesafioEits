Stockontrol.controller('UsersController',function($scope, $http, $mdToast, $window)
{
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
		selectedUsers: [],
		selectedUser: null,
		request: {
			content: [],
		},
		page: 1,
		pageSize: 5,
		// Para exibir/esconder a barrinha de progresso indeterminado
		tasks: 0,
	}

	/*
	 * Métodos
	 */

	// Busca os usuários filtrados lá no serviço
	$scope.fetchUsers = function(text, active, profile, page, limit)
	{
		$scope.model.tasks++;
		text = text || $scope.model.filters.text;
		active = active || $scope.model.filters.active;
		profile = profile || $scope.model.filters.profile;
		page = page || $scope.model.page - 1;
		pageRequest = new PageRequest();
		pageRequest.page = page;
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
	/*
	 * Inicialização
	 */

	// Buscando a lista inicial de usuários
	$scope.fetchUsers();

	// Atualizando a lista de usuários conforme os filtros
	$scope.$watch('model.filters.active', function(){ $scope.fetchUsers(); });
	$scope.$watch('model.filters.profile', function(){ $scope.fetchUsers(); });
	// Para não chamar o serviço demais, não usamos watch na input de busca
});
