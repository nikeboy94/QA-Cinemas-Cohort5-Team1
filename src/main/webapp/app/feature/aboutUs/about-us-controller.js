(function () {

    var AboutUsController = function () {
        var vm = this;

        function createMap() {
            var location = {lat: 53.474564, lng: -2.286641};

            var map = new google.maps.Map(document.getElementById("cinemaMap"), {
                zoom: 16,
                center: location
            });

            var marker = new google.maps.Marker({
                position: location,
                map: map,
                title: "QA Cinemas"
            });
            marker.setMap(map);
        }
        createMap();

    };

    angular.module('movieApp').controller('aboutUsController', [AboutUsController]);
}());