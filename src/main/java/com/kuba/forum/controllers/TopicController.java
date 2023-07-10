package com.kuba.forum.controllers;

import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.model.Post;
import com.kuba.forum.model.Thread;
import com.kuba.forum.controllers.utils.ModelUtils;
import com.kuba.forum.model.Topic;
import com.kuba.forum.services.IPostService;
import com.kuba.forum.services.IThreadService;
import com.kuba.forum.services.ITopicService;
import com.kuba.forum.session.SessionData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/topic")
public class TopicController {
    @Autowired
    IThreadService threadService;
    @Autowired
    ITopicService topicService;
    @Autowired
    IPostService postService;
    @Autowired
    IUserDAO userDAO;
    @Resource
    SessionData sessionData;

    @GetMapping(path = "/{topicId}")
    public String viewTopic(@PathVariable int topicId, Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("topic", this.topicService.findTopicById(topicId));
        model.addAttribute("threads", this.threadService.getThreadsInTopic(topicId));
        model.addAttribute("authors", this.userDAO);
        model.addAttribute("replys", this.postService);
        return "topic-content";
    }


}
