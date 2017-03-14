(function() {

    var AddTicketController = function(ticketDal) {
        var vm = this;

        this.ticketArray = [];

        this.addText = function(ticket, qty) {


            ticket.orderId=new Date().getTime()
            for (var i = 0; i < qty; i++) {

                var obj = {
                    text: "something"
                };

                console.log(i);
                this.ticketArray.push(ticket);
            }

        }


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