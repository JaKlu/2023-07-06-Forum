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
        loginError.innerHTML = "";
    }

    return result;
}