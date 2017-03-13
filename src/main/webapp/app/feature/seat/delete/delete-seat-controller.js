(function() {

    var DeleteSeatController =  function($state, seatDal) {
        var vm = this;

        vm.deleteSeat = function(seatToDelete) {
            console.log("This is the value of seat to delete " + seatToDelete);
            console.log(seatToDelete);
            var movieToJson = JSON.stringify(seatToDelete);
            console.log(seatToJson);
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