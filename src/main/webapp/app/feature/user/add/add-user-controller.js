(function(){

    var AddUserController = function($state, userDAL){

        var vm = this;
        vm.addUser = function(userToAdd){
            userDAL.saveUser(userToAdd).then(function (results){
                vm.userAddMessage = results;
                $state.go('dashboard');
            }, function (error){
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };
    angular.module("movieApp").controller("addUserController", ["$state", "userDAL", AddUserController]);
}());