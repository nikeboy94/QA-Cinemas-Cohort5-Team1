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
        }

        vm.getTickets();

    };
    angular.module("movieApp").controller("getTicketsController", ["$rootScope", "ticketDal", GetTicketController]);
}());