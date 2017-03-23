(function () {


    var AddTicketController = function ($rootScope, ticketDal, Auth, $state, movieDal, showingDal, $modal) {
        var vm = this;

        vm.ticketArray = [];
        vm.tempChildTickets = 0;
        vm.tempAdultTickets = 0;

        vm.addTicket = function (ticket, adultQty, childQty) {
            if($rootScope.globals.currentUser == undefined || $rootScope.globals.currentUser.seats == undefined) {
                alert("Please pick a seat");
                return;
            }

            if (ticket == undefined) {
                ticket = {};
                ticket.user = {};
                ticket.showing = {};

                if($rootScope.globals.currentUser.showing == undefined ) {
                    alert("Please pick a showing");
                    return;
                }

                ticket.showing = $rootScope.globals.currentUser.showing;

                if(vm.overrideEmail != undefined) {
                    ticket.user.email = vm.overrideEmail;
                } else if ($rootScope.globals.currentUser.email != undefined)  {
                    ticket.user.email = $rootScope.globals.currentUser.email;
                } else {
                    alert("Please enter an email address");
                    return;
                }

            } else if (ticket.user == undefined) {
                ticket.user = {};

                if(vm.overrideEmail != undefined) {
                    ticket.user.email = vm.overrideEmail;
                } else {
                    ticket.user.email = $rootScope.globals.currentUser.email;
                }
            } else if (ticket.showing == undefined) {
                ticket.showing = {};
                if($rootScope.globals.currentUser.showing == undefined) {
                    alert("Please pick a showing!");
                    return;
                }
                ticket.showing = $rootScope.globals.currentUser.showing;
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

            if($rootScope.globals.currentUser.seats.length != (parseInt(adultQty) + parseInt(childQty))) {
                alert("Please pick all your seats!");
                return;
            }


            var addChildTickets = function () {
                ticketDal.getPrice(ticket.showing.showingId, 'CHILD').then(function (result) {
                    ticketDalSuccess(result, childQty, 'CHILD');
                    Auth.addOrder(vm.ticketArray);

                    $('#myBookingModal').modal('toggle');

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

        };

        vm.updateEmail = function(email) {
            vm.overrideEmail = email;
        }



        vm.showSeatViewer = function(adultQty, childQty, ticket) {
            if(adultQty == undefined || adultQty < 0 ) {
                adultQty = 0;
            }

            if(childQty == undefined || childQty < 0) {
                childQty = 0;
            }

            if(adultQty + childQty == 0) {
                alert("Please select at least 1 seat");
                return;
            }

            if (ticket == undefined){
                ticket = {};
                ticket.user = {};
                ticket.showing = {};

                if($rootScope.globals.currentUser != undefined) {
                    ticket.user.email= $rootScope.globals.currentUser.email;
                    ticket.showing.showingId = $rootScope.globals.currentUser.showingId;
                }

                if($rootScope.globals.currentUser.showingId == undefined) {
                    alert("Please pick a showing");
                    return;
                }

            } else if (ticket.user == undefined) {
                ticket.user = {};
                if($rootScope.globals.currentUser.email != undefined) {
                    ticket.user.email= $rootScope.globals.currentUser.email;
                }
            } else if (ticket.showing == undefined){
                ticket.showing = {};
                if($rootScope.globals.currentUser.showingId == undefined) {
                    alert("Please pick a showing");
                    return;
                } else {
                    ticket.showing.showingId = $rootScope.globals.currentUser.showingId;
                }

            }


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
                if($rootScope.globals.goToQuickBook != undefined && $rootScope.globals.goToQuickBook == true) {
                    $rootScope.globals.goToQuickBook = false;
                    vm.autoFillMovie = $rootScope.globals.movieTitle;
                    vm.getShowingsById($rootScope.globals.movieId);
                }
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


        vm.updateGlobalPrices = function (showing) {
            ticketDal.getPrice(showing.showingId, 'ADULT').then(function (result) {
                vm.globalAdultPrice = result.price;
                vm.updatePrice();
                ticketDal.getAvailableTickets(showing.showingId).then(function(result) {
                    vm.availableTickets = result.availableTickets;
                })
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            })

            ticketDal.getPrice(showing.showingId, 'CHILD').then(function (result) {
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