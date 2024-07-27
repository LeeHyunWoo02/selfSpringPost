package com.example.demo.service;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private MemberRepository memberRepository;

//    private Member member;

    public boolean login(String userId, String password) {

        Member member = memberRepository.findByUserId(userId);

        if (member != null){
            System.out.println("DB에서 찾은 회원: " + member.getUserId());
            return member.getPassword().equals(password);
        }
        System.out.println("회원 정보가 없습니다.");
        return false;
    }

}
