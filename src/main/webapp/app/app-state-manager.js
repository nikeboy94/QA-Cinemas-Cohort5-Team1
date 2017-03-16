angular.module('movieApp').config(['$stateProvider', '$urlRouterProvider', 'USER_ROLES',
    function ($stateProvider, $urlRouterProvider, USER_ROLES) {

        // For any unmatched url, redirect to /
        $urlRouterProvider.otherwise("/dashboard");

        $stateProvider.state("dashboard", {
            url: "/dashboard",
            templateUrl: "app/feature/dashboard/dashboard-partial.html"
        }).state("addmovie", {
            url: "/addmovie",
            templateUrl: "app/feature/movie/add/add-movie-partial.html"
            /**data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor, USER_ROLES.guest]
            }*/
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
        }).state("contactus", {
            url: "/contactus",
            templateUrl: "app/feature/contactUs/contact-us.html"
        }).state("getmoviebytitle", {
            url: "/getmoviebytitle",
            templateUrl: "app/feature/movie/get-by-title/get-by-title.html"
        }).state("getmoviebygenre", {
            url: "/getmoviebygenre",
            templateUrl: "app/feature/movie/get-by-genre/get-movie-by-genre-partial.html"
        }).state("getshowings", {
            url: "/getshowings",
            templateUrl: "app/feature/showing/get/get-showing.html"
        }).state("addshowing", {
            url: "/addshowing",
            templateUrl: "app/feature/showing/add/add-showing.html"
        }).state("deleteshowing", {
            url: "/deleteshowing",
            templateUrl: "app/feature/showing/delete/delete-showing.html"
        }).state("updateshowing", {
            url: "/updateshowing",
            templateUrl: "app/feature/showing/update/update-showing.html"
        }).state("getshowingsbymovie", {
            url: "/getshowingbymovie",
            templateUrl: "app/feature/showing/get-by-movie/get-showing-by-movie.html"
        }).state("gettickets", {
            url: "/gettickets",
            templateUrl: "app/feature/ticket/get/get-user-tickets.html"
        }).state("updateticket", {
            url: "/updateticket",
            templateUrl: "app/feature/ticket/update/update-ticket.html"
        }).state("addticket", {
            url: "/addticket",
            templateUrl: "app/feature/ticket/add/add-ticket.html"
        }).state("addticketstandalone", {
            url: "/addticketstandalone",
            templateUrl: "app/feature/ticket/add/add-ticket-standalone.html"
        }).state("getavailableticket", {
            url: "/getavailableticket",
            templateUrl: "app/feature/ticket/get-available-for-showing/get-available-for-showing.html"
        }).state("deleteticket", {
            url: "/deleteticket",
            templateUrl: "app/feature/ticket/delete/delete-ticket.html"
        }).state("searchresults",{
            url: "/searchresults",
            templateUrl: "app/feature/movie/search/search-results.html"
        }).state("addmovieposter", {
            url: "/addmovieposter",
            templateUrl: "app/feature/movie/add-movie-poster/add-movie-poster-partial.html"
        }).state("addseat", {
            url: "/addseat",
            templateUrl: "app/feature/seat/add/add-seat.html"
        }).state("getseat", {
            url: "/getseat",
            templateUrl: "app/feature/seat/get/get-seat.html"
        }).state("deleteseat", {
            url: "/deleteseat",
            templateUrl: "app/feature/seat/delete/delete-seat.html"
        }).state("payment", {
            url: "/payment",
            templateUrl: "app/feature/payment/payment.html"
         }).state("viewer", {
            url: "/viewer",
            templateUrl: "app/feature/seat/viewer/viewer.html"
        }).state("aboutus", {
            url: "/aboutus",
            templateUrl: "app/feature/aboutUs/about-us.html"
        }).state("help", {
            url: "/help",                   //Unimplemented
            templateUrl: "app/feature/help/help.html"
        }).state("cookies", {
            url: "/cookies",                //Unimplemented
            templateUrl: "app/feature/cookies/cookies.html"
        }).state("addscreen", {
            url: "/addscreen",
            templateUrl: "app/feature/screen/add/add-screen.html"
        }).state("getscreen", {
            url: "/getscreen",
            templateUrl: "app/feature/screen/get/get-screen.html"
        }).state("deletescreen", {
            url: "/deletescreen",
            templateUrl: "app/feature/screen/delete/delete-screen.html"

        })

    }]);