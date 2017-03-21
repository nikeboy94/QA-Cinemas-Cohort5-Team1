'use strict';

angular.module('movieApp')
    .factory('Auth', ['$http', '$rootScope', '$window', '$cookieStore', 'Session', 'AUTH_EVENTS', 'userDAL',
        function ($http, $rootScope, $window, $cookieStore, Session, AUTH_EVENTS, userDAL) {
            var authService = {};


            //the login function
            authService.login = function (user, success, error) {
                var userData = userDAL.authAttempt(user.email, user.password).then(function (results) {
                    if ((results.email == user.email)) {
                        var loginData = results;
                        $window.sessionStorage["userInfo"] = JSON.stringify(loginData);
                        Session.create(loginData);
                        $rootScope.globals.currentUser = loginData;
                        authService.setRatings();
                        authService.setCredentials();
                        $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                        success(loginData);
                    } else {
                        $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
                        error();
                    }
                }, function (error) {
                    return error;
                });

            };
            authService.setTicketQuantity = function (qty) {
                if ($rootScope.globals.currentUser == undefined) {
                    $rootScope.globals.currentUser = {};
                }
                $rootScope.globals.currentUser.ticketQuantity = qty;
            };

            authService.setShowingId = function (id){
                if ($rootScope.globals.currentUser == undefined){
                    $rootScope.globals.currentUser = {};
                }
                $rootScope.globals.currentUser.showingId = id;
            };

            authService.setCredentials = function () {
                $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.email;
                $cookieStore.put('globals', $rootScope.globals);
            };

            authService.clearCredentials = function () {
                $rootScope.globals = {};
                $cookieStore.remove('globals');
                $http.defaults.headers.common.Authorization = 'Basic ';
            };


            authService.getTicketQuantity = function () {
                return $rootScope.globals.currentUser.ticketQuantity;
            };


            authService.addOrder = function (ticketArray) {
            	for (var i = 0; i < ticketArray.length; i++) {
                    ticketArray[i].seat = {};
            		ticketArray[i].seat.seatId = $rootScope.globals.currentUser.seats[i];
				}
                $rootScope.globals.currentUser.order = ticketArray;
            };

            authService.getOrder = function () {
                return $rootScope.globals.currentUser.order;
            };


            authService.addCard = function (card) {
                if ($rootScope.globals.currentUser == undefined) {
                    $rootScope.globals.currentUser = {};
                }
                $rootScope.globals.currentUser.card = card;
            };

            authService.getCard = function () {
                return $rootScope.globals.currentUser.card;
            };


            authService.setSeats = function (seats) {
                if ($rootScope.globals.currentUser == undefined) {
                    $rootScope.globals.currentUser = {};
                }
                $rootScope.globals.currentUser.seats = seats;
            };

            authService.getSeats = function () {
                return $rootScope.globals.currentUser.seats;

            };
            authService.setShowingId = function (showingId) {
                if ($rootScope.globals.currentUser == undefined) {
                    $rootScope.globals.currentUser = {};
                }
                $rootScope.globals.currentUser.showingId = showingId;
            };

            authService.getShowingId = function () {
                return $rootScope.globals.currentUser.showingId;
            };

            //check if the user is authenticated
            authService.isAuthenticated = function () {
                return !!Session.user;
            };

            //check if the user is authorized to access the next route
            //this function can be also used on element level
            //e.g. <p ng-if="isAuthorized(authorizedRoles)">show this only to admins</p>
            authService.isAuthorized = function (authorizedRoles) {
                if (!angular.isArray(authorizedRoles)) {
                    authorizedRoles = [authorizedRoles];
                }
                return (authService.isAuthenticated() &&
                authorizedRoles.indexOf(Session.role) !== -1);
            };

            //log out the user and broadcast the logoutSuccess event
            authService.logout = function () {
                Session.destroy();
                $window.sessionStorage.removeItem("userInfo");
                $rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
                $rootScope.globals.currentUser.email = undefined;
            };
            
            authService.setRatings = function () {
            	$rootScope.globals.currentUser.ratings = [];
            }

            return authService;
        }]);