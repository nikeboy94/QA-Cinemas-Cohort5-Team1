(function(){

    var GetAvailableForShowingController = function(ticketDal)
    {
        var vm = this;
        vm.test = "error message"

        vm.getAvailableForShowing = function(showingId) {
            ticketDal.getAvailableTickets(showingId).then(function (results) { //function if successful
                vm.availableTickets = results;
            }, function (error){  //function if fail
                vm.error = true;
                vm.errorMessage = error;
            });
        }

    };
    angular.module("movieApp").controller("getAvailableForShowingController", ["ticketDal", GetAvailableForShowingController]);
}());
