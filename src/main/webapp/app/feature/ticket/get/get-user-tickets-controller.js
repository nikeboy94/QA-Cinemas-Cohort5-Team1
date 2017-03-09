(function(){

    var GetTicketController = function(ticketDal)
    {
        var vm = this;

        vm.getTickets = function(email) {
            ticketDal.getTickets("a1@email.com").then(function (results) { //function if successful
                vm.tickets = results;
            }, function (error){  //function if fail
                vm.error = true;
                vm.errorMessage = error;
            });
        }

    };
    angular.module("movieApp").controller("getTicketsController", ["ticketDal", GetTicketController]);
}());