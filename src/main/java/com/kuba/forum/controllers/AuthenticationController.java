package com.kuba.forum.controllers;

import com.kuba.forum.controllers.utils.ModelUtils;
import com.kuba.forum.exceptions.LoginAlreadyExistException;
import com.kuba.forum.exceptions.UserValidationException;
import com.kuba.forum.model.User;
import com.kuba.forum.services.IAuthenticationService;
import com.kuba.forum.session.SessionData;
import com.kuba.forum.validators.UserValidator;
import jakarta.annotation.Resource;
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
    public String login(Model model,
                        @RequestParam(required = false) String info) {
        if (info == null) {
            model.addAttribute("info", "");
        } else if (info.equals("1")) {
            model.addAttribute("info", "Niepoprawny login lub hasło");
        } else if (info.equals("2")) {
            model.addAttribute("info", "Rejestracja zakończona sukcesem. Zaloguj się na swoje konto.");
        }
        ModelUtils.addCommonDataToModel(model, sessionData);
        return "login";
    }

    @PostMapping(path = "/login")
    public String login(@RequestParam String login,
                        @RequestParam String password) {
        this.authenticationService.authenticate(login, password);
        if (sessionData.isLogged()) {
            return "redirect:/main";
        }
        return "redirect:/login?info=1";
    }

    @GetMapping(path = "/logout")
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/main";
    }

    @GetMapping(path = "/register")
    public String register(Model model,
                           @RequestParam(required = false) String info,
                           @RequestParam(required = false) String login) {
        if (info == null) {
            model.addAttribute("info", "");
        } else if (info.equals("1")) {
            model.addAttribute("info", "Użytkownik " + login + " już istnieje");
        } else if (info.equals("2")) {
            model.addAttribute("info", "Wprowadź poprawne dane do formularza");
        }

        ModelUtils.addCommonDataToModel(model, sessionData);
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
            return "redirect:/register?info=1&login=" + user.getLogin();
        } catch (UserValidationException e) {
            return "redirect:/register?info=2";
        }
        return "redirect:/login?info=2";
    }
}