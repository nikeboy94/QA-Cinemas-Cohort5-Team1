(function () {


    var AddTicketController = function(ticketDal, Auth, $state, movieDal, showingDal, $modal) {
        var vm = this;

        vm.ticketArray = [];
        vm.tempChildTickets;
        vm.tempAdultTickets;

        this.addTicket = function (ticket, adultQty, childQty) {

            var addChildTickets = function () {
                ticketDal.getPrice(ticket.showing.showingId, 'CHILD').then(function(result) {
                    ticketDalSuccess(result, childQty, 'CHILD');
                    Auth.addOrder(vm.ticketArray);
                }), function(error) {
                    ticketDalFailure(error);
                }
            }

            var ticketDalSuccess = function (result, qty, ticketType) {
                var thisTicket = initNewTicket();
                thisTicket.ticketType = ticketType;
                thisTicket.price = result.price;
                for (var i = 0; i < qty; i++) {
                    vm.ticketArray.push(thisTicket);
                }
            }

            var ticketDalFailure = function(error) {
                vm.error = true;
                vm.errorMessage = errorMessage;
            }

            var initNewTicket = function() {
                var newTicket = {};
                newTicket.orderId = ticket.orderId;
                newTicket.showing = ticket.showing;
                newTicket.user = ticket.user;

                return newTicket;
            };

            ticket.orderId = new Date().getTime();

            var childTicket = initNewTicket();
            var adultTicket = initNewTicket();
            adultTicket.ticketType = 'ADULT';
            childTicket.ticketType = 'CHILD';

            ticketDal.getPrice(ticket.showing.showingId, 'ADULT').then(function(result) {
                ticketDalSuccess(result, adultQty, 'ADULT');
                addChildTickets();
            }), function(error) {
                ticketDalFailure(error);
            }

            // $state.go('dashboard');

        };


        vm.showSeatViewer = function(adultQty, childQty, ticket) {
            Auth.setShowingId(ticket.showing.showingId);
            Auth.setTicketQuantity(parseInt(adultQty) + parseInt(childQty));
              vm.modalInstance = $modal.open({
                templateUrl : 'app/feature/seat/viewer/viewer.html',
                controller : "viewercontroller",
                backdrop:'static'

            });}

            vm.init = function(){

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
                    alert(error);
                    vm.error = true;
                    vm.errorMessage = error;
                }

            };

            vm.updatePrice = function() {
                console.log("entered update price");
                console.log("adult ticket: " + JSON.stringify(adultQty));
            }

        };

        angular.module('movieApp').controller('addTicketController', ['ticketDal', 'Auth', '$state', 'movieDal', 'showingDal', '$modal', AddTicketController]);

    }());