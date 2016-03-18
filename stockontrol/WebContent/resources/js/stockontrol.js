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
}

var Stockontrol = angular.module('Stockontrol',['ngMaterial', 'ngMessages', 'ui.router', 'md.data.table'])

Stockontrol.config(function($mdThemingProvider, $stateProvider, $urlRouterProvider)
{
	$urlRouterProvider.otherwise('users'); //FIXME

	$stateProvider.state('users', {
		url: '/users',
		templateUrl: 'resources/views/users/users-index.html',
		controller: 'UsersController'
	}).state('categories', {
		url: '/categories',
		templateUrl: 'resources/views/categories/categories-index.html',
		controller: 'CategoriesController'
	});
});
