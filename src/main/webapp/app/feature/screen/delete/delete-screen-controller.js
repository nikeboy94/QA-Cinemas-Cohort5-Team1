(function () {

    var DeleteScreenController = function ($state, screenDal) {
        var vm = this;

        vm.deleteScreen = function (screenToDelete) {
            screenDal.deleteScreen(screenToDelete).then(function(results){
                vm.screenAddMessage = results;
                $state.go("getscreen");
            }, function(error){
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };

    angular.module("movieApp").controller("deleteScreenController", ["$state", "screenDal", DeleteScreenController]);
}());