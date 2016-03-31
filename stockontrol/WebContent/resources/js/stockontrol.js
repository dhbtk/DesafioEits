function serializeObject(o)
{
	var s = '';
	var i = 0;
	for(var k in o)
	{
		if(i != 0)
		{
			s = s + '&';
		}
		s = s + encodeURIComponent(k) + '=' + encodeURIComponent(o[k]);
		if(i == 0)
		{
			i++;
		}
	}
	return s;
};

var Stockontrol = angular.module('Stockontrol',['ngMaterial', 'ngMessages', 'ui.router', 'md.data.table']);

Stockontrol.config(function($mdThemingProvider, $stateProvider, $urlRouterProvider)
{
	$urlRouterProvider.otherwise('report');

	$stateProvider.state('users', {
		url: '/users',
		templateUrl: 'resources/views/users/users-index.html',
		controller: 'UsersController'
	}).state('categories', {
		url: '/categories',
		templateUrl: 'resources/views/categories/categories-index.html',
		controller: 'CategoriesController'
	}).state('products', {
		url: '/products',
		templateUrl: 'resources/views/products/products-index.html',
		controller: 'ProductsController'
	}).state('batches', {
		url: '/batches',
		templateUrl: 'resources/views/batches/batches-index.html',
		controller: 'BatchesController'
	}).state('report', {
		url: '/report',
		templateUrl: 'resources/views/report/report.html',
		controller: 'ReportController'
	});
});
