(function () {

    var viewercontroller = function () {


        var vm = this;
        vm.test="LOL";
vm.counter=0;
        vm.checkboxChanged = function(id) {
             if ($('#'+id).attr('checked')){
                vm.counter--;
                ($('#'+id).attr('checked',false));
            }else {
                ($('#'+id).attr('checked',true));
                vm.counter++;
            }
            if (vm.counter==2){
                var checkboxes = $('#seatmap').find('input[type="checkbox"]');
                var checkedboxes = checkboxes.filter(":checked");
               var uncheckedboxes = checkboxes.not(checkedboxes);
               uncheckedboxes.prop('disabled', true);
               //$('#seatmap').find('input[type="checkbox"]').prop("disabled",true);
            }
            if (vm.counter<2){
                $('#seatmap').find('input[type="checkbox"]').prop("disabled",false);
            }

        };
    };
    angular.module("movieApp").controller("viewercontroller", [viewercontroller]);
}());
