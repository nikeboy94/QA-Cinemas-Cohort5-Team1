(function () {

    var viewercontroller = function ($state, Auth,$modalStack) {


        var vm = this;
        vm.test = "LOL";
        vm.counter = 0;
        vm.tickets =2; // Auth.getTicketQuantity();
        vm.reservedSeats = [{
            seatId: '1A'
        }];

        vm.bookedSeats = [];
        vm.reserved = function () {
            for (var i = 0; i < vm.reservedSeats.length; i++) {
                ($('#' + vm.reservedSeats[i].seatId)).parent().addClass('reserved');
                ($('#' + vm.reservedSeats[i].seatId)).parent().click(false);


            }
        };
        vm.reserved();
        vm.submitSeats = function () {
            if (vm.counter == vm.tickets) {
                Auth.setSeats(vm.bookedSeats);
                $modalStack.dismissAll();
            }
            else{
                alert("Please select "+vm.new+" more seats!")
            }
        };


        vm.checkboxChanged = function (id) {


            if ($('#' + id).attr('checked')) {
                var index = vm.bookedSeats.indexOf(id);
                vm.bookedSeats.splice(index, 1);
                ($('#' + id).attr('checked', false));
                vm.counter--;
            } else {
                ($('#' + id).attr('checked', true));
                vm.bookedSeats.push(id);
                vm.counter++;
            }
            if (vm.counter == vm.tickets) {
                var checkboxes = $('#seatmap').find('input[type="checkbox"]');
                var checkedboxes = checkboxes.filter(":checked");
                var uncheckedboxes = checkboxes.not(checkedboxes);
                uncheckedboxes.prop('disabled', true);

            }
            if (vm.counter < vm.tickets) {
                $('#seatmap').find('input[type="checkbox"]').prop("disabled", false);
            }
            vm.new = +vm.tickets-vm.counter;

        };

    };

    angular.module("movieApp").controller("viewercontroller", ['$state', 'Auth', '$modalStack', viewercontroller]);
}());
