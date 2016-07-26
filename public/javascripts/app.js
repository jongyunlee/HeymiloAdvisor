var app = angular.module('Heymilo', ['ui.router']);

app.config([
    '$stateProvider',
    '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
	$stateProvider
	    .state('home', {
		url: '/home',
		templateUrl: '/home.html',
		controller: 'MainCtrl',
		resolve: {
		    productPromise: ['products', function(products) {
			return products.getAll();
		    }]
		}
	    }).state('products', {
		url: '/products/{id}',
		templateUrl: '/products.html',
		controller: 'ProductsCtrl',
		resolve: {
		    product: ['$stateParams', 'products', function($stateParams, products) {
			return products.get($stateParams.id);
		    }]
		}
	    }).state('login', {
		url: '/login',
		templateUrl: '/login.html',
		controller: 'AuthCtrl',
		onEnter: ['$state', 'auth', function($state, auth) {
		    if (auth.isLoggedIn()) {
			$state.go('home');
		    }
		}]
	    }).state('register', {
		url: '/register',
		templateUrl: '/register.html',
		controller: 'AuthCtrl',
		onEnter: ['$state', 'auth', function($state, auth) {
		    if (auth.isLoggedIn()) {
			$state.go('home');
		    }
		}]
	    });

	$urlRouterProvider.otherwise('home');
    }
]);

app.factory('auth', ['$http', '$window', function($http, $window) {
    var auth = {};
    auth.saveToken = function(token) {
    	$window.localStorage['heymilo-token'] = token;
    };

    auth.getToken = function() {
    	return $window.localStorage['heymilo-token'];
    };

    auth.isLoggedIn = function() {
    	var token = auth.getToken();
    	if (token) {
    	    var payload = JSON.parse($window.atob(token.split('.')[1]));
    	    return payload.exp > Date.now() / 1000;
    	} else {
    	    return false;
    	}
    };
    auth.currentUser = function() {
    	if (auth.isLoggedIn()) {
    	    var token = auth.getToken();
    	    var payload = JSON.parse($window.atob(token.split('.')[1]));
    	    return payload.user_id;
    	}
    };
    auth.register = function(user){
    	return $http.post('/register', user).success(function(data){
    	    auth.saveToken(data.token);
    	});
    };
    auth.logIn = function(user){
    	return $http.post('/login', user).success(function(data){
    	    auth.saveToken(data.token);
    	});
    };
    auth.logOut = function(){
    	$window.localStorage.removeItem('heymilo-token');
    };
    return auth;
}]);

app.factory('products', ['$http', 'auth', function($http, auth) {
    var o = {
	products: []
    };
    o.getAll = function() {
	return $http.get('/products').success(function(data) {
	    angular.copy(data, o.products);
	});
    };
    o.create = function(product) {
	return $http.post('/products', product, {
	    headers: {Authorization: 'Bearer ' + auth.getToken()}
	}).success(function(data) {
	    o.products.push(data);
	});
    };
    o.get = function(id) {
    	return $http.get('/products/' + id).then(function(res) {
    	    return res.data;
    	});
    };
    o.addComment = function(id, comment) {
	return $http.post('/products/' + id +'/comments', comment, {
	    headers: {Authorization: 'bearer ' + auth.getToken()}
	});
    };
    return o;
}]);

app.controller('MainCtrl', [
    '$scope',
    'products',
    'auth',
    function($scope, products, auth) {
	$scope.products = products.products;
	$scope.isLoggedIn = auth.isLoggedIn;
	$scope.addProduct = function(){
	    if(!$scope.name || $scope.name === '') { return; }
	    if(!$scope.price || $scope.price === '') { return; }
	    products.create({
		name: $scope.name,
		price: $scope.price
	    });
	    $scope.name = '';
	    $scope.price = '';
	};

	// slide
	$scope.slideIndex = 1;
	$scope.showDivs = function(n) {
	    var i;
	    var x = document.getElementsByClassName("mySlides");
	    if (n > x.length) {$scope.slideIndex = 1}
	    if (n < 1) {$scope.slideIndex = x.length}
	    for (i = 0; i < x.length; i++) {
		x[i].style.display = "none";
	    }
	    x[$scope.slideIndex-1].style.display = "block";
	}
	$scope.showDivs($scope.slideIndex);

	$scope.plusDivs = function(n) {
	    $scope.showDivs($scope.slideIndex += n);
	}
	carousel();
	function carousel() {
	    var i;
	    var x = document.getElementsByClassName("mySlides");
	    for (i = 0; i < x.length; i++) {
		x[i].style.display = "none";
	    }
	    $scope.slideIndex++;
	    if ($scope.slideIndex > x.length) {$scope.slideIndex = 1}
	    x[$scope.slideIndex-1].style.display = "block";
	    setTimeout(carousel, 10000); // Change image every 10 seconds
	}
    }
]);


app.controller('ProductsCtrl', [
    '$scope',
    'products',
    'product',
    function($scope, products, product){
	$scope.product = product;
	$scope.addComment = function(){
	    if($scope.body === '') { return; }
	    products.addComment(product._id, {
		body: $scope.body,
		author: 'user',
	    }).success(function(comment) {
		$scope.product.comments.push(comment);
	    });
	    $scope.body = '';
	};
    }
]);

app.controller('AuthCtrl', [
    '$scope',
    '$state',
    'auth',
    function($scope, $state, auth) {
	$scope.user = {};
	$scope.register = function() {
	    auth.register($scope.user).error(function(error) {
		$scope.error = error;
	    }).then(function() {
		$state.go('home');
	    });
	};
	$scope.logIn = function() {
	    auth.logIn($scope.user).error(function(error) {
		$scope.error = error;
	    }).then(function() {
		$state.go('home');
	    });
	};
    }
]);

app.controller('NavCtrl', [
    '$scope',
    '$state',
    'auth',
    function($scope, $state, auth){
	$scope.isLoggedIn = auth.isLoggedIn;
	$scope.currentUser = auth.currentUser;
	$scope.logOut = auth.logOut;
	$scope.showLogin = function() {
	    var x = document.getElementById("dropdown-login");
	    if (x.className.indexOf("w3-show") == -1)
		x.className += " w3-show";
	    else
		x.className = x.className.replace(" w3-show", "");
	};
	$scope.showSmallLogin = function() {
	    var x = document.getElementById("small-dropdown-login");
	    if (x.className.indexOf("w3-show") == -1)
		x.className += " w3-show";
	    else
		x.className = x.className.replace(" w3-show", "");
	};
	$scope.user = {};
	$scope.register = function() {
	    auth.register($scope.user).error(function(error) {
		$scope.error = error;
	    }).then(function() {
		$state.go('home');
	    });
	};
	$scope.logIn = function() {
	    auth.logIn($scope.user).error(function(error) {
		$scope.user = {};
		$scope.error = error;
	    }).then(function() {
		$scope.user = {};
		$state.go('home');
	    });
	};
    }
]);
