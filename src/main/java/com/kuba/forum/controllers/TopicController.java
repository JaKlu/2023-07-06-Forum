package com.kuba.forum.controllers;

import com.kuba.forum.controllers.utils.ModelUtils;
import com.kuba.forum.model.Topic;
import com.kuba.forum.services.IThreadService;
import com.kuba.forum.services.ITopicService;
import com.kuba.forum.session.SessionData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/topic")
public class TopicController {
    @Autowired
    IThreadService threadService;
    @Autowired
    ITopicService topicService;
    @Resource
    SessionData sessionData;

    @GetMapping(path = "/{topicId}")
    public String viewTopic(@PathVariable int topicId, Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("topic", this.topicService.findTopicById(topicId).get());
        model.addAttribute("threads", this.threadService.getTopicContent(topicId));
        return "topic-content";
    }

    @GetMapping(path = "/new-topic")
    public String createTopic(Model model) {
        if (!this.sessionData.isAdmin()) {
            return "redirect:/main";
        }
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("adding", true);
        model.addAttribute("topicModel", new Topic());

        return "new-topic";
    }

    @PostMapping(path = "/new-topic")
    public String createTopic(@ModelAttribute Topic topic) {
        if (!this.sessionData.isAdmin()) {
            return "redirect:/main";
        }
        topic = this.topicService.addTopic(topic);

        return "redirect:/topic/" + topic.getId();
    }

    @GetMapping(path = "/{topicId}/delete-topic")
    public String deleteThread(@PathVariable int topicId) {
        if (!this.sessionData.isAdmin()) {
            return "redirect:/main";
        }
        this.topicService.deleteTopic(topicId);

        return "redirect:/main";
    }

    @GetMapping(path = "/{topicId}/edit-topic")
    public String editTopic(Model model,
                            @PathVariable int topicId) {
        Optional<Topic> topicBox = this.topicService.findTopicById(topicId);
        if (topicBox.isPresent() && this.sessionData.isAdmin()) {
            ModelUtils.addCommonDataToModel(model, sessionData);
            model.addAttribute("editing", true);
            model.addAttribute("topic", topicBox.get());
            model.addAttribute("topicModel", topicBox.get());
            return "new-topic";
        }
        return "redirect:/main";
    }

    @PostMapping(path = "/{topicId}/edit-topic")
    public String editTopic(@ModelAttribute Topic topic,
                            @PathVariable int topicId) {
        if (!this.sessionData.isAdmin()) {  //TODO security
            return "redirect:/login";
        }
        topic.setId(topicId);
        this.topicService.editTopic(topic);

        return "redirect:/topic/" + topicId;
    }
}