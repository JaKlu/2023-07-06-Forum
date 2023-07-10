function validate() {
    var subject = document.getElementById("subject");
    var subjectError = document.getElementById("subject-error");

    var contents = document.getElementById("contents");
    var contentsError = document.getElementById("contents-error");


    var subjectRegex = /^.+$/;
    var contentsRegex = /^.+$/;


    var result = true;

    if(!subjectRegex.test(subject.value)) {
        subjectError.innerHTML = "Wpisz temat";
        subjectError.classList.add("error-on");
        result = false;
    } else {
        subjectError.innerHTML = "";
    }

    if(!contentsRegex.test(contents.value)) {
        contentsError.innerHTML = "Wpisz treść wiadomości";
        contentsError.classList.add("error-on");
        result = false;
    } else {
        contentsError.innerHTML = "";
    }

    return result;
}