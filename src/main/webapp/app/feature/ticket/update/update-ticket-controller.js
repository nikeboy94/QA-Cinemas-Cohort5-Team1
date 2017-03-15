(function(){

    var UpdateTicketController = function(ticketDal)
    {
        var vm = this;

        vm.updateTicket = function(ticketIdToUpdate, updatedTicket) {
            ticketDal.updateTicket(ticketIdToUpdate, updatedTicket).then(function (results) { //function if successful
                vm.returnMessage = results;
            }, function (error){  //function if fail
                vm.error = true;
                vm.errorMessage = error;
            });
        }

    };
    angular.module("movieApp").controller("updateTicketController", ["ticketDal", UpdateTicketController]);
}());