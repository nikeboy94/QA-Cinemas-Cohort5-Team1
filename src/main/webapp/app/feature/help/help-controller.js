(function () {
    var HelpController = function ($state) {
        var vm = this;

        vm.contactus = function () {
            $state.go("contactus");
        }

        vm.glyphicon = function(){
            this.class.removeClass()
        }

    };
    angular.module('movieApp').controller('helpController', ['$state', HelpController]);
}());