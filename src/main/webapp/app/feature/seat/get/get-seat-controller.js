(function(){

    var GetSeatController = function(seatDAL)
    {
        var vm = this;
        vm.test = "getSeatTest";

        function init(){
            seatDAL.getSeat().then(function (results) {        //function if successful
                vm.seat = results;
            }, function (error){                                //function if fail
                vm.error = true;
                vm.errorMessage = error;
            });
        }
        init();

    };
    angular.module("movieApp").controller("getSeatController", ["seatDAL", GetSeatController]);
}());