package com.kuba.forum.controllers;

import com.kuba.forum.controllers.utils.ModelUtils;
import com.kuba.forum.exceptions.LoginAlreadyExistException;
import com.kuba.forum.exceptions.UserValidationException;
import com.kuba.forum.model.User;
import com.kuba.forum.services.IAuthenticationService;
import com.kuba.forum.session.SessionData;
import com.kuba.forum.validators.UserValidator;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {
    @Autowired
    IAuthenticationService authenticationService;
    @Resource
    SessionData sessionData;

    @GetMapping(path = "/login")
    public String login(Model model) {
        model.addAttribute("info", this.sessionData.getInfo());
        ModelUtils.addCommonDataToModel(model, sessionData);
        return "login";
    }

    @PostMapping(path = "/login")
    public String login(@RequestParam String login,
                        @RequestParam String password) {
        this.authenticationService.authenticate(login, password);
        if (sessionData.isLogged()) {
            return "redirect:" + this.sessionData.getLastPath();
        }
        this.sessionData.setInfo("Niepoprawny login lub hasło");
        return "redirect:/login";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        this.authenticationService.logout(request);
        return "redirect:/main";
    }

    @GetMapping(path = "/register")
    public String register(Model model,
                           @RequestParam(required = false) String info) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        model.addAttribute("userModel", new User());
        return "register";
    }

    @PostMapping(path = "/register")
    public String register(@ModelAttribute User user,
                           @RequestParam String password2) {
        try {
            UserValidator.validateUser(user);
            UserValidator.validatePasswordEquality(user.getPassword(), password2);
            this.authenticationService.register(user);
        } catch (LoginAlreadyExistException e) {
            this.sessionData.setInfo("Użytkownik " + user.getLogin() + " już istnieje");
            return "redirect:/register";
        } catch (UserValidationException e) {
            this.sessionData.setInfo("Wprowadź poprawne dane do formularza");
            return "redirect:/register";
        }
        this.sessionData.setInfo("Rejestracja zakończona sukcesem. Zaloguj się na swoje konto.");
        return "redirect:/login";
    }
}