package com.example.demo.service;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    // 회원가입 데이터 저장 기능
    public void save(Member member) {

        //db에 이미 동일한 userID를 가진 회원이 존재하는지?
        boolean isUser = memberRepository.existsByUserId(member.getUserId());
        if(isUser){
            throw new IllegalArgumentException("이미 존재하는 사용자 ID입니다.");
        }

        member.setRole("ROLE_USER");
        memberRepository.save(member);
    }


}
