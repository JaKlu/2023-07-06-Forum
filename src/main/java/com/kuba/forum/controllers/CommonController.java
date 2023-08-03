package com.kuba.forum.controllers;

import com.kuba.forum.controllers.utils.ModelUtils;
import com.kuba.forum.model.view.Link;
import com.kuba.forum.services.IPostService;
import com.kuba.forum.services.ITopicService;
import com.kuba.forum.services.IUserService;
import com.kuba.forum.session.SessionData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class CommonController {
    @Autowired
    IUserService userService;
    @Autowired
    ITopicService topicService;
    @Autowired
    IPostService postService;
    @Resource
    SessionData sessionData;

    @GetMapping(path = {"/main", "/"})
    public String homePage(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("topics", this.topicService.getForumContent());
        return "index";
    }

    @GetMapping(path = "/search")
    public String search(Model model,
                         @RequestParam(required = false) String query) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("directory", new Link("Szukaj", "/search"));
        if (query == null || query.equals("")) {
            model.addAttribute("posts", new ArrayList<>());
        } else {
            model.addAttribute("posts", this.postService.getPostsFromQuery(query));
        }
        return "search";
    }

    @GetMapping(path = "/users")
    public String users(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("directory", new Link("Użytkownicy", "/users"));
        model.addAttribute("users", this.userService.getUsersContent());
        return "users";
    }
}
