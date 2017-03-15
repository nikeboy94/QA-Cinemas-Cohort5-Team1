function styleChange(caller){
    var view = caller.id;
    var folder = caller.closest("ul").id;
    var currentStyle = document.getElementById("viewStyle").id;
    if(currentStyle != "css/viewStyleSheets/" + folder + "/" + view + "-style.css") {
        $("#viewStyle").prop("href", "css/viewStyleSheets/" + folder + "/" + view + "-style.css");
    }
}