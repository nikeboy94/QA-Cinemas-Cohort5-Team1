(function() {

    var PaymentController =  function($state, $scope, movieDal, dal, ticketDal, Auth, $window) {
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
            paypal.Button.render({

                env: 'sandbox', // Optional: specify 'sandbox' environment

                client: {
                    sandbox:    'AR_cO7Py4bLzYgHr2XfOGnBpW0jpBW7TE7tN-BnQng7Jat4W358Hz02xnJogCLSXFR1mcxIGyvuhFil7',
                },

                payment: function() {

                    var env    = this.props.env;
                    var client = this.props.client;

                    return paypal.rest.payment.create(env, client, {
                        transactions: [
                            {
                                amount: { total: vm.price, currency: 'GBP' }
                            }
                        ]
                    });
                },

                commit: true, // Optional: show a 'Pay Now' button in the checkout flow

                onAuthorize: function(data, actions) {

                    // Optional: display a confirmation page here

                    return actions.payment.execute().then(function() {
                        // Show a success page to the buyer
                    });
                }

            }, '#paypal-button');
        };
        vm.init();

        vm.submitNewPayment = function(card) {
            vm.card = card;
            vm.formatCardNumber();
            vm.card.expiryDate = vm.card.expiryMonth + vm.card.expiryYear;
            vm.card.cardholderName = "jane doe";
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
                ticketDal.addOrder(vm.formatOrder()).then(function(result) {
                    Auth.clearOrder();
                    if($window.sessionStorage["userInfo"] == undefined) {
                        ticketDal.sendGuestConfirmation(vm.order[0].orderId, vm.order[0].user.email);
                    } else {
                        ticketDal.sendConfirmation(vm.order[0].orderId);
                    }
                });
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

    angular.module('movieApp').controller('paymentController', ['$state', '$scope', 'movieDal', 'dal', 'ticketDal', 'Auth', '$window', PaymentController]);
}());
