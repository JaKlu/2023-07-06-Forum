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

    var gender = document.getElementById("gender");
    var genderError = document.getElementById("gender-error");

    var place = document.getElementById("place");
    var placeError = document.getElementById("place-error");


    var loginRegex = /^.{2,}$/;
    var passwordRegex = /^.{2,}$/;
    var password2Regex = /^.{2,}$/;
    var emailRegex = /^.{2,}$/;
    var birthdayRegex = /^.{2,}$/;
    var genderRegex = /^.{2,}$/;
    var placeRegex = /^.{2,}$/;


    var result = true;


    if(!loginRegex.test(login.value)) {
        loginError.innerHTML = "Podaj poprawny login";
        loginError.classList.add("error-on");
        result = false;
    } else {
        loginError.innerHTML = "";
        loginError.style.background = "#ffffff";
    }

    if(!passwordRegex.test(password.value)) {
        passwordError.innerHTML = "Podaj porawne hasło";
        passwordError.classList.add("error-on");
        result = false;
    } else {
        passwordError.style.background = "#ffffff";
    }

    return result;
}