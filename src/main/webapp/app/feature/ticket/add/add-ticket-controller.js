(function() {

    var AddTicketController = function(ticketDal, Auth, $state, $scope, $modal) {
        var vm = this;

        this.ticketArray = [];

        this.addText = function(ticket, qty) {
            Auth.setTicketQuantity(qty);
            $state.go('dashboard');

            ticket.orderId=new Date().getTime()
            for (var i = 0; i < qty; i++) {

                var obj = {
                    text: "something"
                };

                console.log(i);
                this.ticketArray.push(ticket);
            }

        };
        var modalInstance=null;
        vm.showSeatViewer = function() {
              vm.modalInstance = $modal.open({
                templateUrl : 'app/feature/seat/viewer/viewer.html',
                controller : "viewercontroller",
                backdrop:'static'

            });
        };

        vm.addTicket = function(ticketToAdd) {

            ticketDal.addTicket(ticketToAdd).then(function (results) {
                vm.ticketAddMessage = results;
            }, function (error) {
                vm.error=true;
                vm.errorMessage = error;

            });


        };
    };

    angular.module('movieApp').controller('addTicketController', ['ticketDal', 'Auth', '$state', '$scope', '$modal', AddTicketController]);

}());