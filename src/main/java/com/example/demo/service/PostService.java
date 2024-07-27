package com.example.demo.service;

import com.example.demo.Repository.PostRepository;
import com.example.demo.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void save(Post post) {
        postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAllPosts();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null); // 게시물이 없을 경우 null 반환
    }
}
