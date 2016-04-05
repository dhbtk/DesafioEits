var Stockontrol = angular.module('Stockontrol',['ngMaterial', 'ngMessages', 'ui.router', 'md.data.table']);

Stockontrol.config(function($mdThemingProvider, $stateProvider, $urlRouterProvider, $httpProvider)
{
	$urlRouterProvider.otherwise('batches');

	$stateProvider.state('system', {
		'abstract': true,
		resolve: {
			authorize: function(auth)
			{
				return auth.authorize();
			}
		},
		templateUrl: 'resources/views/system.html'
	}).state('users', {
		parent: 'system',
		url: '/users',
		templateUrl: 'resources/views/users/users-index.html',
		controller: 'UsersController'
	}).state('categories', {
		parent: 'system',
		url: '/categories',
		templateUrl: 'resources/views/categories/categories-index.html',
		controller: 'CategoriesController'
	}).state('products', {
		parent: 'system',
		url: '/products',
		templateUrl: 'resources/views/products/products-index.html',
		controller: 'ProductsController'
	}).state('batches', {
		parent: 'system',
		url: '/batches',
		templateUrl: 'resources/views/batches/batches-index.html',
		controller: 'BatchesController'
	}).state('report', {
		parent: 'system',
		url: '/report',
		templateUrl: 'resources/views/report/report.html',
		controller: 'ReportController'
	}).state('login', {
		url: '/login',
		templateUrl: 'resources/views/login.html',
		controller: 'LoginController',
		resolve: {
			authorize: function(auth)
			{
				return auth.authorize();
			}
		}
	});
})
.factory('identity', function($q, $rootScope)
{
	var _currentUser = undefined, _authenticated = false;
	return {
		identityPresent: function()
		{
			return angular.isDefined(_currentUser);
		},
		authenticated: function()
		{
			return _authenticated;
		},
		admin: function()
		{
			return angular.isDefined(_currentUser) && _currentUser.profile == "Administrator";
		},
		authenticate: function(id)
		{
			_currentUser = id;
			_authenticated = id != null;
		},
		currentUser: function()
		{
			var def = $q.defer();

			if(angular.isDefined(_currentUser))
			{
				def.resolve(_currentUser);
				return def.promise;
			}
			else
			{
				userService.getCurrent({
					callback: function(user)
					{
						console.log(user);
						_currentUser = user;
						_authenticated = true;
						def.resolve(_currentUser);
					},
					errorHandler: function()
					{
						_currentUser = null;
						_authenticated = false;
						def.resolve(_currentUser);
					},
					exceptionHandler: function()
					{
						_currentUser = null;
						_authenticated = false;
						def.resolve(_currentUser);
					},
				});
				return def.promise;
			}
		}
	};
})
.factory('auth', function($rootScope, $state, identity)
{
	return {
		authorize: function()
		{
			return identity.currentUser().then(function()
			{
				if(!identity.authenticated() && $rootScope.toState.url != '/login')
				{
					$rootScope.returnToState = $rootScope.toState;
					$state.go('login');
				}
			});
		}
	};
})
.run(function($rootScope, $state, $stateParams, auth, identity)
{
	$rootScope.$on('$stateChangeStart', function(event, toState, toStateParams)
	{
		$rootScope.toState = toState;

		if(identity.identityPresent())
		{
			auth.authorize();
		}
	});
});
