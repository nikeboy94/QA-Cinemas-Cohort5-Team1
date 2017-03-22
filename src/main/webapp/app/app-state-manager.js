angular.module('movieApp').config(['$stateProvider', '$urlRouterProvider', 'USER_ROLES',
    function ($stateProvider, $urlRouterProvider, USER_ROLES) {

        // For any unmatched url, redirect to /
        $urlRouterProvider.otherwise("/dashboard");

        $stateProvider.state("dashboard", {
            url: "/dashboard",
            templateUrl: "app/feature/dashboard/dashboard-partial.html"
        }).state("addmovie", {
            url: "/addmovie",
            templateUrl: "app/feature/movie/add/add-movie-partial.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("getmovie", {
            url: "/getmovie",
            templateUrl: "app/feature/movie/get/get-movie-partial.html"
        }).state("updatemovie", {
            url: "/updatemovie",
            templateUrl: "app/feature/movie/update/update-movie-partial.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("deletemovie", {
            url: "/deletemovie",
            templateUrl: "app/feature/movie/delete/delete-movie-partial.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("adduser", {
            url: "/adduser",
            templateUrl: "app/feature/user/add/add-user.html"
        }).state("getuser", {
            url: "/getuser",
            templateUrl: "app/feature/user/get/get-user.html",
            data: {
                authorizedRoles: [USER_ROLES.admin]
            }
        }).state("updateuser", {
            url: "/updateuser",
            templateUrl: "app/feature/user/update/update-user.html",
            data: {
                authorizedRoles: [USER_ROLES.admin]
            }
        }).state("deleteuser", {
            url: "/deleteuser",
            templateUrl: "app/feature/user/delete/delete-user.html",
            data: {
                authorizedRoles: [USER_ROLES.admin]
            }
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
            templateUrl: "app/feature/showing/get/get-showing.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("addshowing", {
            url: "/addshowing",
            templateUrl: "app/feature/showing/add/add-showing.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("deleteshowing", {
            url: "/deleteshowing",
            templateUrl: "app/feature/showing/delete/delete-showing.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("updateshowing", {
            url: "/updateshowing",
            templateUrl: "app/feature/showing/update/update-showing.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("getshowingsbymovie", {
            url: "/getshowingbymovie",
            templateUrl: "app/feature/showing/get-by-movie/get-showing-by-movie.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("gettickets", {
            url: "/gettickets",
            templateUrl: "app/feature/ticket/get/get-user-tickets.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("updateticket", {
            url: "/updateticket",
            templateUrl: "app/feature/ticket/update/update-ticket.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("addticket", {
            url: "/addticket",
            templateUrl: "app/feature/ticket/add/add-ticket.html"
        }).state("addticketstandalone", {
            url: "/addticketstandalone",
            templateUrl: "app/feature/ticket/add/add-ticket-standalone.html"
        }).state("getavailableticket", {
            url: "/getavailableticket",
            templateUrl: "app/feature/ticket/get-available-for-showing/get-available-for-showing.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("deleteticket", {
            url: "/deleteticket",
            templateUrl: "app/feature/ticket/delete/delete-ticket.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("searchresults", {
            url: "/searchresults",
            templateUrl: "app/feature/movie/search/search-results.html"
        }).state("addmovieposter", {
            url: "/addmovieposter",
            templateUrl: "app/feature/movie/add-movie-poster/add-movie-poster-partial.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("addseat", {
            url: "/addseat",
            templateUrl: "app/feature/seat/add/add-seat.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("getseat", {
            url: "/getseat",
            templateUrl: "app/feature/seat/get/get-seat.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("deleteseat", {
            url: "/deleteseat",
            templateUrl: "app/feature/seat/delete/delete-seat.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
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
            url: "/help",
            templateUrl: "app/feature/help/help.html"
        }).state("cookies", {
            url: "/cookies",
            templateUrl: "app/feature/cookies/cookies.html"
        }).state("addscreen", {
            url: "/addscreen",
            templateUrl: "app/feature/screen/add/add-screen.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("getscreen", {
            url: "/getscreen",
            templateUrl: "app/feature/screen/get/get-screen.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("deletescreen", {
            url: "/deletescreen",
            templateUrl: "app/feature/screen/delete/delete-screen.html",
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        }).state("allorderssummary", {
        	url: "/allorderssummary",
        	templateUrl: "app/feature/orderSummary/all-user-orders.html"
        }).state("ordersummary", {
        	url: "/ordersummary",
        	templateUrl: "app/feature/orderSummary/order-summary.html"
        })
    }]);