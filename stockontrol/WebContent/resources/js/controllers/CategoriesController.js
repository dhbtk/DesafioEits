Stockontrol.controller('CategoriesController',function($controller, $scope, $mdToast, $mdSidenav, $mdDialog, identity)
{
	// Herdando do controller base
	$controller('BaseController', {$scope: $scope});

	/** Localização do cabeçalho **/
	$scope.header.location = '/resources/views/categories/categories-header.html';

	/*
	 * Modelo
	 */
	angular.extend($scope.model, {
		filters: ['text'],
		filterData: {
			text: null,
		},
		category: null,
		order: 'name',
	});

	/*
	 * Métodos
	 */

	$scope.openEdit = function(id)
	{
		productService.findCategory(id, function(category)
		{
			$scope.category = category;
			$mdSidenav('rightPanel').open();
		});
	};

	$scope.openNew = function()
	{
		$mdDialog.show({
			controller: 'NewCategoryController',
			templateUrl: '/resources/views/categories/categories-new.html',
			scope: $scope.$new(),
			clickOutsideToClose: true
		});
	};

	$scope.destroy = function(category)
	{
		var dialog = $mdDialog.confirm()
				.title('Excluir categoria')
				.textContent('Tem certeza que quer excluir a categoria "' + category.name + '"? Todos os produtos e lotes associados serão perdidos. Esta ação não pode ser desfeita.')
				.ok('Excluir')
				.cancel('Cancelar');
		$mdDialog.show(dialog).then(function()
		{
			productService.deleteCategory(category.id, {
				callback: function()
				{
					$mdSidenav('rightPanel').close();
					$mdToast.show(
							$mdToast.simple()
									.textContent('Categoria "' + category.name + '" excluída')
									.position('bottom')
									.hideDelay(3000));
					$scope.fetchData();
				},
				errorHandler: function(ex, msg)
				{
					console.log(ex);
					console.log(msg);
				}
			});
		});
	};

	/**
	 *
	 * Listagem
	 *
	 */

	/*
	 * Inicialização
	 */

	$scope.fetchFunction = productService.listAllCategoriesByFilters;
});
