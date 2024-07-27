package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class JoinController {

    @Autowired
    private final MemberService memberService;


    // 회원가입 페이지 로딩
    @GetMapping("/home/join/")
    public String showJoinForm(){

        return "join";
    }




    // 회원가입 페이지
    @PostMapping("/joinProc")
    public String join(@RequestParam("user_name") String username, @RequestParam("nickname") String nickname,
                       @RequestParam("user_id") String userId, @RequestParam("password") String password,
                       @RequestParam("age") int age, @RequestParam("address") String address,
                       @RequestParam("email") String email, Model model){
//        System.out.println("회원가입 요청 접수됨");


        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickname);
        member.setUserId(userId);
        member.setPassword(password);
        member.setAge(age);
        member.setAddress(address);
        member.setEmail(email);

        try {
            memberService.save(member);

            model.addAttribute("user_name", username);
            model.addAttribute("nickname", nickname);
            model.addAttribute("user_id", userId);
            model.addAttribute("password", password);
            model.addAttribute("age", age);
            model.addAttribute("address", address);
            model.addAttribute("email", email);

            return "join_result";

        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "join";  // 회원가입 폼 페이지로 돌아감 (에러 메시지 포함)
        } catch (Exception e) {
            model.addAttribute("errorMessage", "회원가입 중 오류가 발생했습니다.");
            return "join";  // 회원가입 폼 페이지로 돌아감 (일반 오류 메시지 포함)
        }
    }

    @GetMapping("/home/join_result/")
    public String joinResult() {
        return "join_result";
    }
}
