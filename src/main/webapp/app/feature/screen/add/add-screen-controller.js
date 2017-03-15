(function(){
    var AddScreenController = function(screenDal){

        var vm = this;
        vm.addScreen = function(screenToAdd){
            screenDal.saveScreen(screenToAdd).then(function (results){
                vm.screenAddMessage = results;

            }, function (error){
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };
    angular.module("movieApp").controller("addScreenController", ["screenDal", AddScreenController]);
}());