package com.kuba.forum.controllers;

import com.kuba.forum.controllers.utils.ModelUtils;
import com.kuba.forum.exceptions.LoginAlreadyExistException;
import com.kuba.forum.exceptions.UserValidationException;
import com.kuba.forum.model.User;
import com.kuba.forum.model.view.Link;
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
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("directory", new Link("Logowanie", "/login"));
        if (this.sessionData.isLogged()) {
            return "redirect:/";
        }
        model.addAttribute("formError", this.sessionData.getFormError());
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        return "login";
    }

    @PostMapping(path = "/login")
    public String login(@RequestParam String login,
                        @RequestParam String password) {
        this.authenticationService.authenticate(login, password);
        if (sessionData.isLogged()) {
            return "redirect:" + this.sessionData.getLastPath();
        }
        this.sessionData.setFormError("Niepoprawny login lub hasło");
        return "redirect:/login";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        this.authenticationService.logout(request);
        return "redirect:/main";
    }

    @GetMapping(path = "/register")
    public String register(Model model,
                           @RequestParam(required = false) String formInfo,
                           @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("directory", new Link("Rejestracja", "/register"));
        if (this.sessionData.isLogged()) {
            return "redirect:/";
        }
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
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
            this.sessionData.setFormError("Użytkownik " + user.getLogin() + " już istnieje");
            return "redirect:/register";
        } catch (UserValidationException e) {
            this.sessionData.setFormError("Wprowadź poprawne dane do formularza");
            return "redirect:/register";
        }
        this.sessionData.setFormInfo("Rejestracja zakończona sukcesem. Zaloguj się na swoje konto.");
        return "redirect:/login";
    }
}