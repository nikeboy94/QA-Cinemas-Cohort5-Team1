"use strict";

(function () {

    angular.module('movieApp').config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/getmovie");

        $stateProvider.state("dashboard", {
            url: "/dashboard",
            templateUrl: "app/feature/dashboard/dashboard-partial.html"
        }).state("addmovie", {
            url: "/addmovie",
            templateUrl: "app/feature/movie/add/add-movie-partial.html"
        }).state("getmovie", {
            url: "/getmovie",
            templateUrl: "app/feature/movie/get/get-movie-partial.html"
        })
    });
}());