function validate() {
    var contents = document.getElementById("contents");
    var contentsError = document.getElementById("contents-error");

    var contentsRegex = /^.+$/;

    var result = true;

    if(!contentsRegex.test(contents.value)) {
        contentsError.innerHTML = "Wpisz treść wiadomości";
        contentsError.classList.add("error-on");
        result = false;
    } else {
        contentsError.innerHTML = "";
    }

    return result;
}