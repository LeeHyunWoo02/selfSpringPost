package com.example.demo.Repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member save(Member member);

    // UserId 중복 확인
    boolean existsByUserId(String userId);

    Member findByUserId(String userId);

}
