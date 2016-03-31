Stockontrol.controller('ReportController', function($controller, $scope)
{
	$controller('BaseController', {$scope: $scope});

	$scope.header.location = '/resources/views/report/report-header.html';

	angular.extend($scope.model, {
		filters: ['categoryId', 'name'],
		filterData: {
			categoryId: null,
			name: null,
		},
		product: null,
		order: 'name',
		limit: 5,
	});

	function loadProductBatches(product)
	{
		product.expiringBatches = [];
		product.expiredBatches = [];
		batchService.listAllExpiring(product.id, null, function(data)
		{
			data.content.sort(function(a, b) { return a.expiresAt - b.expiresAt});
			product.expiringBatches = data;
		});
		batchService.listAllExpired(product.id, null, function(data)
		{
			data.content.sort(function(a, b) { return a.expiresAt - b.expiresAt});
			product.expiredBatches = data;
		});
	}

	$scope.fetchFunctionCallback = function(data)
	{
		for(var i = 0; i < data.content.length; i++)
		{
			loadProductBatches(data.content[i]);
		}
	};

	$scope.fetchFunction = productService.listAllProductsByFilters;
});
