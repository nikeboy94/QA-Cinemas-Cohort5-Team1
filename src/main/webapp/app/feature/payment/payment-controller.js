(function() {

    var PaymentController =  function($state, $scope, movieDal, dal) {
        var vm = this;

        vm.createMerchantSessionKey = function() {
            dal.http.POST_MSK().then(function (results) {
                vm.payment = results;
                vm.submitCardDetails();
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        }
        vm.createMerchantSessionKey();

        vm.submitCardDetails = function() {
            vm.card = {};
            vm.card.cardholderName = "jane doe";
            vm.card.cardNumber = "4929000000006";
            vm.card.expiryDate = "0518";
            vm.card.securityCode = "123";
            dal.http.POST_CARD(vm.payment.merchantSessionKey, vm.card).then(function (results) {
                vm.cardIdentifier = results;
                vm.submitPayment()
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };

        vm.submitPayment = function() {
            alert(vm.cardIdentifier.cardIdentifier);
            dal.http.POST_PAYMENT(vm.payment.merchantSessionKey, vm.cardIdentifier.cardIdentifier, 100).then(function (results) {
                alert(JSON.stringify(results));
                vm.cardIdentifier = results;
            }, function (error) {
                alert(JSON.stringify(error));
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };

    angular.module('movieApp').controller('paymentController', ['$state', '$scope', 'movieDal', 'dal', PaymentController]);
}());
