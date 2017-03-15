(function() {

    var PaymentController =  function($state, $scope, movieDal, dal, Auth) {
        var vm = this;

        vm.submitNewPayment = function(card) {
            vm.card = card;
            vm.card.expiryDate = vm.card.expiryMonth + vm.card.expiryYear;
            vm.card.cardholderName = "jane doe";
            Auth.addCard(vm.card);
            alert(JSON.stringify(Auth.getCard()));
            vm.createMerchantSessionKey();
        }

        vm.createMerchantSessionKey = function() {
            dal.http.POST_MSK().then(function (results) {
                vm.payment = results;
                vm.submitCardDetails();
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        }


        vm.submitCardDetails = function() {
            dal.http.POST_CARD(vm.payment.merchantSessionKey, vm.card).then(function (results) {
                vm.cardIdentifier = results;
                vm.submitPayment()
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };

        vm.submitPayment = function() {
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

    angular.module('movieApp').controller('paymentController', ['$state', '$scope', 'movieDal', 'dal', 'Auth', PaymentController]);
}());
