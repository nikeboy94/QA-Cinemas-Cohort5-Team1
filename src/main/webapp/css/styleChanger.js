function styleChange(caller){
    var view = caller.id;                       //ID of the button pressed
    var folder = caller.closest("ul").id;       //ID of the <ul> that contains the button
    var currentStyle = document.getElementById("viewStyle").id;
    if(currentStyle != "css/viewStyleSheets/" + folder + "/" + view + "-style.css") {               //eg css/viewStyleSheets/movie/add-movie-style.css
        $("#viewStyle").prop("href", "css/viewStyleSheets/" + folder + "/" + view + "-style.css");
    }
}