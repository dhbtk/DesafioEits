Stockontrol.controller('BaseController', function($q, $scope)
{
	$scope.model = {
		filters: [],
		filterData: {},
		request: {
			content: [],
		},
		order: 'id',
		page: 1,
		limit: 10,
		tasks: 0,
	};

	/**
	 * Esse valor deve ser mudado para a função de filtragem do serviço DWR
	 */
	$scope.fetchFunction = null;

	/**
	 * Método que busca um serviço de filtragem e retorna uma promise
	 */
	$scope.fetchData = function()
	{
		if(!$scope.fetchFunction)
		{
			console.error('Função de filtragem ainda não foi definida!');
			return;
		}
		var filterData = $scope.model.filters.map(function(val) {
			return $scope.model.filterData[val];
		}, this);

		// Inicializando nossa lista de parâmetros
		var params = filterData;

		// Paginação
		var pr = new SimplePageRequest();
		pr.page = $scope.model.page - 1;
		pr.size = $scope.model.limit;
		// Ordenação
		var direction = 'ASC', property;
		if($scope.model.order[0] == '-')
		{
			direction = 'DESC';
			property = $scope.model.order.slice(1);
		}
		pr.property = property;
		pr.direction = direction;

		params.push(pr);
		// Nossa promessa
		var def = $q.defer();
		// Nosso callback
		params.push({
			callback: function(data)
			{
				$scope.model.tasks--;
				$scope.model.request = data;
				$scope.$apply();
				def.resolve(data);
			},
			errorHandler: function()
			{
				$scope.model.tasks--;
				$scope.$apply();
				def.reject(data);
			},
			timeout: 1000,
		});
		// Chamando a função
		$scope.model.tasks++;
		$scope.fetchFunction.apply(null, params);
		$scope.fetchPromise = def.promise;
		return def.promise;
	};

	/**
	 * Paginador de data-tables
	 */
	$scope.paginateTable = function(page, limit)
	{
		console.log('Pedindo página ' + page + ', mostrando ' + limit + ' por página');
		$scope.model.page = page;
		$scope.model.limit = limit;
		$scope.fetchData();
	};

	/**
	 * Ordenador de data-tables
	 */
	$scope.sortTable = function(order)
	{
		$scope.model.order = order;
		$scope.fetchData();
	};

	// Chama $scope.fetchUsers() caso a tecla seja um enter
	$scope.handleSearchBoxKeyPress = function($event)
	{
		if($event.which === 13)
		{
			$scope.fetchData();
		}
	};

	// Pegando a primeira lista depois de definirmos a função de filtragem
	var firstFetch = $scope.$watch('fetchFunction', function(newVal)
	{
		if(newVal)
		{
			firstFetch();
		}
	});

	// Atualizando a lista conforme os filtros
	$scope.$watch('model.filterData', function(){ if($scope.fetchFunction) $scope.fetchData(); }, true);
});
