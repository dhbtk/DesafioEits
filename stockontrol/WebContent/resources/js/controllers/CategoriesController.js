Stockontrol.controller('CategoriesController',function($scope, $mdToast, $mdSidenav, $mdDialog)
{
	/** Localização do cabeçalho **/
	$scope.header.location = '/resources/views/categories/categories-header.html';

	/*
	 * Modelo
	 */
	$scope.model = {
		filters: {
			text: null,
		},
		category: null,
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

	$scope.fetchCategories = function(text, page, limit, sortOrder, sortProp)
	{
		// Deixando em 'loading'
		$scope.model.tasks++;
		// Filtros
		text = text || $scope.model.filters.text;
		// Paginação
		pageRequest = new SimplePageRequest();
		pageRequest.page = (page || $scope.model.page) - 1;
		pageRequest.size = limit || $scope.model.pageSize;
		// Ordem
		pageRequest.property = sortProp || 'id';
		pageRequest.direction = sortOrder || 'ASC';

		productService.listAllCategoriesByFilters(text, pageRequest, {
			callback: function(data)
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
		$scope.fetchCategories(null, page, limit);
	};

	// Chama $scope.fetchUsers() caso a tecla seja um enter
	$scope.handleSearchBoxKeyPress = function($event)
	{
		if($event.which === 13)
		{
			$scope.fetchCategories();
		}
	};
});
