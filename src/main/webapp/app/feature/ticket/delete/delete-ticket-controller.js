(function(){

    var DeleteTicketController = function(ticketDal)
    {
        var vm = this;
        vm.test = "testing delete";

        vm.deleteTicket = function(ticketId) {
            ticketDal.deleteTicket(ticketId).then(function (results) { //function if successful
                vm.returnMessage = results;
            }, function (error){  //function if fail
                vm.error = true;
                vm.errorMessage = error;
            });
        }

    };
    angular.module("movieApp").controller("deleteTicketController", ["ticketDal", DeleteTicketController]);
}());