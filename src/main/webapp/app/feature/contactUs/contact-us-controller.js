function formCheck(){
    var selected = document.getElementById("category").value;
    if(selected == "Other"){
        $("#other").prop("disabled",false);
    }
    else{
        $("#other").prop("disabled",true);
    }
}