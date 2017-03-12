'use strict';

angular.module('movieApp')
.factory('Auth', [ '$http', '$rootScope', '$window', '$cookieStore', 'Session', 'AUTH_EVENTS', 'userDAL',
function($http, $rootScope, $window, $cookieStore, Session, AUTH_EVENTS, userDAL) {
	var authService = {};
	
	
	//the login function
	authService.login = function(user, success, error) {
		var userData = userDAL.authAttempt(user.email, user.password).then(function (results) {
            if((Object.keys(results).length) == 1) {
            	if((results[0].email == user.email) && (results[0].password == user.password)) {
					var loginData = results[0];
                    $window.sessionStorage["userInfo"] = JSON.stringify(loginData);
                    delete loginData.password;
                    Session.create(loginData);
                    $rootScope.globals.currentUser = loginData;
                    authService.setCredentials();
                    $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                    success(loginData);
				} else {
            		$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
                    error();
                }
			}
        }, function (error) {
            return error;
        });
        /**
		$http.get('app/misc/users.json').success(function(data) {
		
		//this is my dummy technique, normally here the 
		//user is returned with his data from the db
		var users = data.users;
		if(users[user.username]){
			var loginData = users[user.username];
			//insert your custom login function here 
			if(user.email == loginData.email && user.password == loginData.username){
				//set the browser session, to avoid relogin on refresh
				$window.sessionStorage["userInfo"] = JSON.stringify(loginData);
				
				//delete password not to be seen clientside 
				delete loginData.password;
				
				//update current user into the Session service or $rootScope.currentUser
				//whatever you prefer
				Session.create(loginData);
				//or
				$rootScope.currentUser = loginData;

				//fire event of successful login
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
				//run success function
				success(loginData);
			} else{
				//OR ELSE
				//unsuccessful login, fire login failed event for 
				//the according functions to run
				$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
				error();
			}
		}	
		});
		 */
		
	};

	authService.setCredentials = function() {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.email;
        $cookieStore.put('globals', $rootScope.globals);
	};

    authService.clearCredentials = function () {
        $rootScope.globals = {};
        $cookieStore.remove('globals');
        $http.defaults.headers.common.Authorization = 'Basic ';
    };

	//check if the user is authenticated
	authService.isAuthenticated = function() {
		return !!Session.user;
	};
	
	//check if the user is authorized to access the next route
	//this function can be also used on element level
	//e.g. <p ng-if="isAuthorized(authorizedRoles)">show this only to admins</p>
	authService.isAuthorized = function(authorizedRoles) {
		if (!angular.isArray(authorizedRoles)) {
	      authorizedRoles = [authorizedRoles];
	    }
	    return (authService.isAuthenticated() &&
	      authorizedRoles.indexOf(Session.userRole) !== -1);
	};
	
	//log out the user and broadcast the logoutSuccess event
	authService.logout = function(){
		Session.destroy();
		$window.sessionStorage.removeItem("userInfo");
		$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
    $rootScope.globals.currentUser.email = undefined;
  }

	return authService;
} ]);