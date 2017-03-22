(function() {

    var PaymentController =  function($state, $scope, movieDal, dal, ticketDal, Auth) {
        var vm = this;

        vm.price = 0;
        vm.tXCode = "";
        vm.displayPrice = 0;
        
        vm.init = function() {
            vm.order = Auth.getOrder();
            vm.tXCode = "qacinemas-" + vm.order[0].orderId;
            for(var i = 0; i < vm.order.length; i++) {
            	vm.price += parseInt(vm.order[i].price);
            	vm.displayPrice += parseFloat(vm.order[i].price);
            };
        };
        vm.init();

        vm.submitNewPayment = function(card) {
            vm.card = card;
            vm.formatCardNumber();
            vm.card.expiryDate = vm.card.expiryMonth + vm.card.expiryYear;
            vm.card.cardholderName = "jane doe";
            Auth.addCard(vm.card);
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
            dal.http.POST_PAYMENT(vm.payment.merchantSessionKey, vm.cardIdentifier.cardIdentifier, vm.price, vm.tXCode).then(function (results) {
                vm.cardIdentifier = results;
                ticketDal.addOrder(vm.formatOrder());
                $state.go("ordersummary");
            }, function (error) {
                alert("Transaction failed. Please try again later.");
                vm.error = true;
                vm.errorMessage = error;
            });
        };

        vm.formatOrder = function() {
            for(var i = 0; i < vm.order.length; i++) {
                vm.order[i].seat.seatId = vm.order[i].showing.screen.screenId + "_" + vm.order[i].seat.seatId;
            }
            return vm.order;
        }
        
        vm.formatCardNumber = function() {
        	var oldNumber = vm.card.cardNumber;
        	var array = oldNumber.split(" ");
        	var newNumber = "";
        	for (var i = 0; i < array.length; i++) {
        		newNumber += array[i];
        	}
        	console.log(JSON.stringify(newNumber));
        	vm.card.cardNumber = newNumber;
        }
    };

    angular.module('movieApp').controller('paymentController', ['$state', '$scope', 'movieDal', 'dal', 'ticketDal', 'Auth', PaymentController]);
}());
