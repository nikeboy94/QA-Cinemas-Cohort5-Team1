(function(){
    var GetScreenController = function(screenDal){
        var vm = this;

        function init(){
            screenDal.getAllScreens().then(function(results) {
                vm.screens = results;
            }, function (error){
                vm.error = true;
                vm.errorMessage = error;
            });
        }
        init();

    };
    angular.module("movieApp").controller("getScreenController", ["screenDal", GetScreenController]);
}());
