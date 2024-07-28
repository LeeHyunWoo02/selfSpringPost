package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Member;
import com.example.demo.domain.Post;
import com.example.demo.service.PostService;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    
    // 게시물 리스트
    @GetMapping("/post")
    public String postHome(HttpSession session, Model model){
        if(session.getAttribute("userId") == null){
            return "redirect:/home/login";
        }
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post";
    }

    // 게시물 작성
    @GetMapping("/post/write")
    public String showWritePage(HttpSession session){
        if(session.getAttribute("userId") == null){
            return "redirect:/home/login";
        }
        return "write";
    }

    @PostMapping("/postProc")
    public String WritePost( @RequestParam("content") String content,
                            @RequestParam("title") String title,
                             HttpSession session,Model model){

        if(session.getAttribute("userId") == null){
            return "redirect:/home/login";
        }

        int user_id = (int) session.getAttribute("Id");
        String nickname = (String) session.getAttribute("nickname");

        Post post = new Post();
        post.setContent(content);
        post.setTitle(title);
        post.setNickname(nickname);

        Member member = new Member();
        member.setId(user_id);
        post.setMember(member);

        postService.save(post);

        model.addAttribute("content", content);
        model.addAttribute("title", title);
        model.addAttribute("nickname", nickname);


        return "redirect:/home_user";

    }

    // 게시물 상세보기
    @GetMapping("/post_detail/{id}")
    public String getPostDetail(@PathVariable("id") long id,HttpSession session ,Model model) {

        if(session.getAttribute("userId") == null){
            return "redirect:/home/login";
        }

        Post post = postService.findById(id);
        model.addAttribute("post", post);

        return "post_detail";
    }

    // 게시물 수정
    @GetMapping("post/edit/{id}")
    public String ShowPostEdit(@PathVariable("id") long id, Model model,HttpSession session){

        if(session.getAttribute("userId") == null){
            return "redirect:/home/login";
        }

        Post post = postService.findById(id);
        model.addAttribute("post", post);

        return "edit";
    }

    @PostMapping("/post/editProc/{id}")
    public String PostEdit(@PathVariable("id") long id, @RequestParam("title") String title,
                           @RequestParam("content") String content,HttpSession session){
        if(session.getAttribute("userId") == null){
            return "redirect:/home/login";
        }

        Post post = postService.findById(id);
        if(post == null){
            return "redirect:/post";
        }

        post.setTitle(title);
        post.setContent(content);
        postService.save(post);

        return "redirect:/post_detail/" + id;
    }

    
}
