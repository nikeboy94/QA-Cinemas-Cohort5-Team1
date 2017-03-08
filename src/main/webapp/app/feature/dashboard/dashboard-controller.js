(function () {

    var DashboardController = function () {
        var vm = this;
        vm.test = "This is the dashboard"
    };

    angular.module('movieApp').controller('dashboardController', [DashboardController]);
}());