function validate() {
    var name = document.getElementById("name");
    var nameError = document.getElementById("name-error");

    var description = document.getElementById("description");
    var descriptionError = document.getElementById("description-error");


    var nameRegex = /^.{3,}$/;
    var descriptionRegex = /^.{10,}$/;


    var result = true;

    if(!nameRegex.test(name.value)) {
        nameError.innerHTML = "Nazwa musi mieć co najmniej 3 znaki";
        nameError.classList.add("error-on");
        result = false;
    } else {
        nameError.innerHTML = "";
    }

    if(!descriptionRegex.test(description.value)) {
        descriptionError.innerHTML = "Opis musi mieć co najmniej 10 znaków";
        descriptionError.classList.add("error-on");
        result = false;
    } else {
        descriptionError.innerHTML = "";
    }

    return result;
}