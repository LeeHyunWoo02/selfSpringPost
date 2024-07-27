package com.example.demo.controller;

import com.example.demo.Repository.PostRepository;
import com.example.demo.domain.Post;
import com.example.demo.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public String postHome(HttpSession session, Model model){
        if(session.getAttribute("userId") == null){
            return "redirect:/home/login";
        }
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post";
    }

    @GetMapping("/post/write")
    public String showWritePage(HttpSession session){
        if(session.getAttribute("userId") == null){
            return "redirect:/home/login";
        }
        return "write";
    }

    @PostMapping("/postProc")
    public String WritePost( @RequestParam("content") String content,
                            @RequestParam("title") String title, Model model){

        Post post = new Post();
        post.setContent(content);
        post.setTitle(title);

        postService.save(post);

        model.addAttribute("content", content);
        model.addAttribute("title", title);

        return "redirect:/home_user";

    }

    @GetMapping("/post_detail/{id}")
    public String getPostDetail(@PathVariable("id") long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "post_detail";
    }

}
