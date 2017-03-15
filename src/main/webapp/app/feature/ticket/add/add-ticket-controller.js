(function () {

    var AddTicketController = function (ticketDal, Auth, $state, movieDal, showingDal) {
        var vm = this;

        vm.ticketArray = [];

        this.addTicket = function (ticket, adultQty, childQty) {

            var addChildTickets = function () {
                ticketDal.getPrice(ticket.showing.showingId, 'CHILD').then(function(result) {
                    ticketDalSuccess(result, childQty, childTicket);
                    Auth.addOrder(vm.ticketArray);
                }), function(error) {
                    ticketDalFailure(error);
                }
            }
            
            var ticketDalSuccess = function (result, qty, specificTicket) {
                specificTicket.price = result.price;
                for (var i = 0; i < qty; i++) {
                    vm.ticketArray.push(specificTicket);
                }
            }

            var ticketDalFailure = function(error) {
                vm.error = true;
                vm.errorMessage = errorMessage;
            }

            var initNewTicket = function() {
                var newTicket = {};
                newTicket.orderId = ticket.orderId;
                newTicket.seat = ticket.seat;
                newTicket.showing = ticket.showing;
                newTicket.user = ticket.user;

                return newTicket;
            };

            ticket.orderId = new Date().getTime();
            Auth.setTicketQuantity(adultQty + childQty);

            var childTicket = initNewTicket();
            var adultTicket = initNewTicket();
            adultTicket.ticketType = 'ADULT';
            childTicket.ticketType = 'CHILD';

            ticketDal.getPrice(ticket.showing.showingId, 'ADULT').then(function(result) {
                ticketDalSuccess(result, adultQty, adultTicket);
                addChildTickets();
            }), function(error) {
                ticketDalFailure(error);
            }

            // $state.go('dashboard');

        
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
                alert(error);
                vm.error = true;
                vm.errorMessage = error;
            }
        };
    };

    angular.module('movieApp').controller('addTicketController', ['ticketDal', 'Auth', '$state', 'movieDal', 'showingDal', AddTicketController]);

}());