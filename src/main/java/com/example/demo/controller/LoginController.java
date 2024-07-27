package com.example.demo.controller;

import com.example.demo.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 메인 홈
    @GetMapping("/")
    public String home(){
        return "home";
    }

    // 로그인 화면 로딩
    @GetMapping("/home/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/home_user")
    public String home_user(HttpSession session){
        if(session.getAttribute("userId") == null){
            return "redirect:/home/login";
        }
        return "home_user";
    }

    @PostMapping("/loginProc")
    public String loginProc(@RequestParam("userId") String userId, @RequestParam("password") String password,
                            HttpSession session){

        if(loginService.login(userId,password)){
            session.setAttribute("userId", userId);
            return "redirect:/home_user";
        }

        return "redirect:/home/login?error=true";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션 무효화
        session.invalidate();
        return "redirect:/";
    }
}
