function checkInput(caller){
    if (!($(caller).val())){
        $(caller).parent().addClass("has-error");
    }
    else {
        $(caller).parent().removeClass("has-error").addClass("has-success");
    }
}


