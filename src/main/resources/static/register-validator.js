function validate() {
    var login = document.getElementById("login");
    var loginError = document.getElementById("login-error");

    var password = document.getElementById("password");
    var passwordError = document.getElementById("password-error");

    var password2 = document.getElementById("password2");
    var password2Error = document.getElementById("password2-error");

    var email = document.getElementById("email");
    var emailError = document.getElementById("email-error");

    var birthday = document.getElementById("birthday");
    var birthdayError = document.getElementById("birthday-error");

    var genders = document.getElementsByName("gender");
    var genderError = document.getElementById("gender-error");

    var place = document.getElementById("place");
    var placeError = document.getElementById("place-error");


    var loginRegex = /^.{2,}$/;
    var passwordRegex = /^.{2,}$/;
    var password2Regex = /^.{2,}$/;
    var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var birthdayRegex = /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/;
    var genderRegex = /^.{2,}$/;
    var placeRegex = /^.+$/;


    var result = true;


    if(!loginRegex.test(login.value)) {
        loginError.innerHTML = "Login musi mieć co najmniej 2 znaki";
        loginError.classList.add("error-on");
        result = false;
    } else {
        loginError.innerHTML = "";
    }

    if(!passwordRegex.test(password.value)) {
        passwordError.innerHTML = "Hasło musi mieć co najmniej 2 znaki";
        passwordError.classList.add("error-on");
        result = false;
    } else {
        passwordError.innerHTML = "";
    }

    if(password.value != password2.value) {
        password2Error.innerHTML =  "Hasła muszą się zgadzać";
        password2Error.classList.add("error-on");
        result = false;
    } else {
        password2Error.innerHTML = "";
    }

    if(!emailRegex.test(email.value)) {
        emailError.innerHTML = "Podaj poprawny email";
        emailError.classList.add("error-on");
        result = false;
    } else {
        emailError.innerHTML = "";
    }

    if(!birthdayRegex.test(birthday.value)) {
        birthdayError.innerHTML = "Podaj poprawną datę urodzenia";
        birthdayError.classList.add("error-on");
        result = false;
    } else {
        birthdayError.innerHTML = "";
    }

    if (!(genders[0].checked == true || genders[1].checked == true)) {
        genderError.innerHTML = "Wybierz płeć";
        genderError.classList.add("error-on");
        result = false;
    } else {
        genderError.innerHTML = "";
    }

    if(!placeRegex.test(place.value)) {
        placeError.innerHTML = "Miejscowość musi mieć co najmniej 1 znak";
        placeError.classList.add("error-on");
        result = false;
    } else {
        placeError.innerHTML = "";
    }

    return result;
}