(function() {

    var AddTicketController = function(ticketDal) {
        var vm = this;

        vm.addTicket = function(ticketToAdd) {
            ticketDal.addTicket(ticketToAdd).then(function (results) {
                vm.ticketAddMessage = results;
            }, function (error) {
                vm.error=true;
                vm.errorMessage = error;

            });


        };
    };

    angular.module('movieApp').controller('addTicketController', ['ticketDal', AddTicketController]);

}());