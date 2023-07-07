function validate() {
    var login = document.getElementById("login");
    var loginError = document.getElementById("login-error");

    var password = document.getElementById("password");
    var passwordError = document.getElementById("password-error");


    var loginRegex = /^.{2,}$/;
    var passwordRegex = /^.{2,}$/;


    var result = true;
    var loginErrorMsg = "";
    var passwordErrorMsg = "";

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