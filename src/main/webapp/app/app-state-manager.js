angular.module('movieApp').config(['$stateProvider', '$urlRouterProvider', 'USER_ROLES',
function($stateProvider, $urlRouterProvider, USER_ROLES) {

  // For any unmatched url, redirect to /
  $urlRouterProvider.otherwise("/dashboard");
    $stateProvider.state("dashboard", {
        url: "/dashboard",
        templateUrl: "app/feature/dashboard/dashboard-partial.html"
    }).state("addmovie", {
        url: "/addmovie",
        templateUrl: "app/feature/movie/add/add-movie-partial.html",
        data: {
            authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor, USER_ROLES.guest]
        }
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
    }).state("getmoviebytitle",{
        url: "/getmoviebytitle",
        templateUrl: "app/feature/movie/get-by-title/get-by-title.html"
    }).state("getmoviebygenre", {
            url: "/getmoviebygenre",
            templateUrl: "app/feature/movie/get-by-genre/get-movie-by-genre-partial.html"
    }).state("getshowings", {
        	url: "/getshowings",
        	templateUrl: "app/feature/showing/get/get-showing.html"
    })
}());
