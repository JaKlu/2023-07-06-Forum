package com.kuba.forum.controllers.utils;

import com.kuba.forum.session.SessionData;
import org.springframework.ui.Model;

public class ModelUtils {
    public static void addCommonDataToModel(Model model, SessionData sessionData) {
        model.addAttribute("user", sessionData.getUser());
        model.addAttribute("logged", sessionData.isLogged());
    }
}
