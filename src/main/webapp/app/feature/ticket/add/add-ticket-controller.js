(function() {

    var AddTicketController = function(ticketDal) {
        var vm = this;
        vm.test = "error here";

        vm.addTicket = function(ticketToAdd) {
            console.log("This is the value of ticket to add " + ticketToAdd);
            console.log(ticketToAdd);
            var ticketToJson = JSON.stringify(ticketToAdd);
            console.log(ticketToJson);
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