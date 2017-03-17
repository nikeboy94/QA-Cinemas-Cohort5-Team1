(function () {
    var HelpController = function ($state) {
        var vm = this;

        vm.contactus = function(){
            $state.go("contactus");
        }


    };
angular.module('movieApp').controller('helpController', ['$state', HelpController]);
}());