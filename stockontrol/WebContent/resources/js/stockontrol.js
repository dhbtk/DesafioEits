var Stockontrol = angular.module('Stockontrol',['ngMaterial', 'ngMessages', 'ui.router', 'md.data.table']);

Stockontrol.config(function($mdThemingProvider, $stateProvider, $urlRouterProvider)
{
	$urlRouterProvider.otherwise('batches');

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
