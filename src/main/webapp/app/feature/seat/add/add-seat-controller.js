(function(){

    var AddSeatController = function($state, seatDAL){

        var vm = this;
        vm.addSeat = function(seatToAdd){
            seatDAL.saveSeat(seatToAdd).then(function (results){
                vm.seatAddMessage = results;
                $state.go('getseat');
            }, function (error){
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };
    angular.module("movieApp").controller("addSeatController", ["$state", "seatDAL", AddSeatController]);
}());