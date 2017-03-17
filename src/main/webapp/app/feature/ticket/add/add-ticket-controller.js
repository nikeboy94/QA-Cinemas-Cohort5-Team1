(function () {


    var AddTicketController = function ($rootScope, ticketDal, Auth, $state, movieDal, showingDal, $modal) {
        var vm = this;

        vm.ticketArray = [];
        vm.tempChildTickets = 0;
        vm.tempAdultTickets = 0;

        this.addTicket = function (ticket, adultQty, childQty) {
            if (ticket == undefined) {
                ticket = {};
                ticket.user = {};
                ticket.showing = {};

                ticket.user.email = $rootScope.globals.currentUser.email;
                ticket.showing.showingId = $rootScope.globals.currentUser.showingId;
            } else if (ticket.user == undefined) {
                ticket.user = {};
                ticket.user.email = $rootScope.globals.currentUser.email;
            } else if (ticket.showing == undefined) {
                ticket.showing = {};
                ticket.showing.showingId = $rootScope.globals.currentUser.showingId;
            }

            if (adultQty == undefined || adultQty < 0) {
                adultQty = 0;
            }
            if (childQty == undefined || childQty < 0) {
                childQty = 0;
            }

            if(adultQty + childQty == 0) {
                return;
            }

            var addChildTickets = function () {
                ticketDal.getPrice(ticket.showing.showingId, 'CHILD').then(function (result) {
                    ticketDalSuccess(result, childQty, 'CHILD');
                    Auth.addOrder(vm.ticketArray);
                    $state.go("payment");
                }), function (error) {
                    ticketDalFailure(error);
                };
            };

            var ticketDalSuccess = function (result, qty, ticketType) {
                for (var i = 0; i < qty; i++) {
                    var thisTicket = initNewTicket();
                    thisTicket.ticketType = ticketType;
                    thisTicket.price = result.price;
                    vm.ticketArray.push(thisTicket);
                }
            };

            var ticketDalFailure = function (error) {
                vm.error = true;
                vm.errorMessage = errorMessage;
            };

            var initNewTicket = function () {
                var newTicket = {};
                newTicket.orderId = ticket.orderId;
                newTicket.showing = ticket.showing;
                newTicket.user = ticket.user;

                return newTicket;
            };

            ticket.orderId = new Date().getTime();

            ticketDal.getPrice(ticket.showing.showingId, 'ADULT').then(function (result) {
                ticketDalSuccess(result, adultQty, 'ADULT');
                addChildTickets();
            }), function (error) {
                ticketDalFailure(error);
            };

            // $state.go('dashboard');
        };


        vm.showSeatViewer = function (adultQty, childQty, ticket) {
            Auth.setShowingId(ticket.showing.showingId);
            Auth.setTicketQuantity(parseInt(adultQty) + parseInt(childQty));
            vm.modalInstance = $modal.open({
                templateUrl: 'app/feature/seat/viewer/viewer.html',
                controller: "viewercontroller",
                backdrop: 'static'

            });
        };

        vm.init = function () {
            movieDal.getMovies().then(function (result) {
                vm.movieList = result;
            }), function (error) {
                vm.error = true;
                vm.errorMessage = error;
            }
        };

        vm.init();

        vm.getShowingsById = function (movieId) {
            showingDal.getShowingByMovie(movieId).then(function (result) {
                vm.movieShowingList = result;
            }), function (error) {
                vm.error = true;
                vm.errorMessage = error;
            }

        };

        vm.updateAdultQty = function (qty) {
            if (qty < 0) {
                return;
            }

            vm.tempAdultTickets = qty;
            vm.updatePrice();
        }

        vm.updateChildQty = function (qty) {
            if (qty < 0) {
                return;
            }

            vm.tempChildTickets = qty;
            vm.updatePrice();
        }

        vm.updatePrice = function () {
            vm.totalPrice = parseFloat(vm.tempAdultTickets * vm.globalAdultPrice) + parseFloat(vm.tempChildTickets * vm.globalChildPrice);
        }


        vm.updateGlobalPrices = function (showingId) {
            ticketDal.getPrice(showingId, 'ADULT').then(function (result) {
                vm.globalAdultPrice = result.price;
                vm.updatePrice();
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            })

            ticketDal.getPrice(showingId, 'CHILD').then(function (result) {
                vm.globalChildPrice = result.price;
                vm.updatePrice();
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            })
        }

    };

    angular.module('movieApp').controller('addTicketController', ['$rootScope', 'ticketDal', 'Auth', '$state', 'movieDal', 'showingDal', '$modal', AddTicketController]);

}());