package com.kuba.forum.controllers;

import com.kuba.forum.controllers.utils.ModelUtils;
import com.kuba.forum.model.Post;
import com.kuba.forum.model.Thread;
import com.kuba.forum.services.IPostService;
import com.kuba.forum.services.IThreadService;
import com.kuba.forum.services.ITopicService;
import com.kuba.forum.services.IUserService;
import com.kuba.forum.session.SessionData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/thread")
public class ThreadController {

    @Autowired
    IUserService userService;
    @Autowired
    IPostService postService;
    @Autowired
    IThreadService threadService;
    @Autowired
    ITopicService topicService;
    @Resource
    SessionData sessionData;

    @GetMapping(path = "/{threadId}")
    public String viewThread(@PathVariable int threadId, Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("topic", this.topicService.findTopicById(
                this.threadService.findThreadById(threadId).get().getTopicId()
        ));
        model.addAttribute("thread", this.threadService.findThreadById(threadId).get());
//        model.addAttribute("posts", this.postService.getPostsFromThread(threadId));
        model.addAttribute("posts", this.postService.getThreadContent(threadId));
//        model.addAttribute("users", this.userService);
        return "thread-content";
    }

    @GetMapping(path = "/new-thread/{topicId}")
    public String createThread(@PathVariable int topicId, Model model) {
        if (!this.sessionData.isLogged()) {
            this.sessionData.setLastPath("/thread/new-thread/" + topicId);
            return "redirect:/login";
        }
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("topic", this.topicService.findTopicById(topicId));
        model.addAttribute("threadModel", new Thread());
        model.addAttribute("postModel", new Post());

        return "new-thread";
    }

    @PostMapping(path = "/new-thread/{topicId}")
    public String createThread(@ModelAttribute Thread thread,
                               @ModelAttribute Post post,
                               @PathVariable int topicId) {
        if (!this.sessionData.isLogged()) {
            return "redirect:/login";
        }
        thread.setAuthorId(sessionData.getUser().getId());
        thread.setTopicId(topicId);
        thread = this.threadService.addThread(thread);

        post.setThreadId(thread.getId());
        post.setAuthorId(sessionData.getUser().getId());
        post = this.postService.addPost(post);

        return "redirect:/thread/" + thread.getId();
    }

    @GetMapping(path = "/{threadId}/add-reply")
    public String addReply(@PathVariable int threadId, Model model) {
        if (!this.sessionData.isLogged()) {
            this.sessionData.setLastPath("/thread/" + threadId + "/add-reply");
            return "redirect:/login";
        }
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("topic", this.topicService.findTopicById(this.threadService.findThreadById(threadId).get().getTopicId()));
        model.addAttribute("thread", this.threadService.findThreadById(threadId).get());
        model.addAttribute("postModel", new Post());

        return "new-reply";
    }

    @PostMapping(path = "/{threadId}/add-reply")
    public String addReply(@ModelAttribute Post post,
                           @PathVariable int threadId) {

        if (!this.sessionData.isLogged()) {
            return "redirect:/login";
        }
        post.setThreadId(threadId);
        post.setAuthorId(sessionData.getUser().getId());
        this.postService.addPost(post);

        return "redirect:/thread/" + threadId;
    }

    @GetMapping(path = "/{threadId}/delete-post/{postId}")
    public String deletePost(@PathVariable int threadId,
                             @PathVariable int postId) {
        if (!this.sessionData.isAdmin()) {
            return "redirect:/thread/" + threadId;
        }
        this.postService.deletePost(postId);
        return "redirect:/thread/" + threadId;
    }

    @GetMapping(path = "/{threadId}/delete-thread")
    public String deleteThread(@PathVariable int threadId) {
        int topicId = this.threadService.findThreadById(threadId).get().getTopicId();
        if (!this.sessionData.isAdmin()) {
            return "redirect:/thread/" + threadId;
        }
        this.threadService.deleteThread(threadId);

        return "redirect:/topic/" + topicId;
    }
}
