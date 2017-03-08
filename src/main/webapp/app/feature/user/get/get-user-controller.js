(function(){

    var GetUserController = function(userDAL)
    {
        var vm = this;
        vm.test = "getUserTest";

        function init(){
            userDAL.getUsers().then(function (results) {        //function if successful
                vm.users = results;
            }, function (error){                                //function if fail
                vm.error = true;
                vm.errorMessage = error;
            });
        }
        init();

    };
    angular.module("movieApp").controller("getUserController", ["userDAL", GetUserController]);
}());