"use strict";

(function() {

    angular.module("movieApp").service("screenDal", ["dal", ScreenDal]);

    function ScreenDal(dal) {

        this.getAllScreens = function() {
            return dal.http.GET("rest/screen/json");
        };
    }
}());
