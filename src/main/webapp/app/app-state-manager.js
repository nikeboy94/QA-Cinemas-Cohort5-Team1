"use strict";

(function () {

    angular.module('movieApp').config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/dashboard");

        $stateProvider.state("dashboard", {
            url: "/dashboard",
            templateUrl: "app/feature/dashboard/dashboard-partial.html"
        }).state("addmovie", {
            url: "/addmovie",
            templateUrl: "app/feature/movie/add/add-movie-partial.html"
        }).state("getmovie", {
            url: "/getmovie",
            templateUrl: "app/feature/movie/get/get-movie-partial.html"
        }).state("updatemovie", {
            url: "/updatemovie",
            templateUrl: "app/feature/movie/update/update-movie-partial.html"
        }).state("deletemovie", {
            url: "/deletemovie",
            templateUrl: "app/feature/movie/delete/delete-movie-partial.html"
        }).state("adduser", {
            url: "/adduser",
            templateUrl: "app/feature/user/add/add-user.html"
        }).state("getuser", {
            url: "/getuser",
            templateUrl: "app/feature/user/get/get-user.html"
        }).state("updateuser", {
            url: "/updateuser",
            templateUrl: "app/feature/user/update/update-user.html"
        }).state("deleteuser", {
            url: "/deleteuser",
            templateUrl: "app/feature/user/delete/delete-user.html"
        }).state("getshowings", {
        	url: "/getshowings",
        	templateUrl: "app/feature/showing/get/get-showing.html"
        })
    });
}());