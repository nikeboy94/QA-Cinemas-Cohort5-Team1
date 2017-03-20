(function(){

    var GetTicketController = function($rootScope, ticketDal)
    {
        var vm = this;

        vm.getTickets = function() {
            ticketDal.getTickets( $rootScope.globals.currentUser.email).then(function (results) { //function if successful
                vm.tickets = results;
            }, function (error){  //function if fail
                vm.error = true;
                vm.errorMessage = error;
            });
        };

        vm.getTickets();

        vm.deleteTicket = function(ticketId) {
            ticketDal.deleteTicket(ticketId).then(function (results) { //function if successful
                vm.returnMessage = results;
            }, function (error){  //function if fail
                vm.error = true;
                vm.errorMessage = error;
            });
        }

    };
    angular.module("movieApp").controller("getTicketsController", ["$rootScope", "ticketDal", GetTicketController]);
}());