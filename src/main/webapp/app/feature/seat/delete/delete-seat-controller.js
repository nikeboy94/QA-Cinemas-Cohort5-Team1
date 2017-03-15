(function() {

    var DeleteSeatController =  function($state, seatDal) {
        var vm = this;

        vm.deleteSeat = function(seatToDelete) {
            seatDal.deleteSeat(seatToDelete).then(function (results) {
                vm.seatAddMessage  = results;
                $state.go('getseat');
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };

    angular.module('movieApp').controller('deleteSeatController', ['$state','seatDal',DeleteSeatController]);
}());