package com.example.demo.Repository;

import com.example.demo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post save(Post post);

    @Query("SELECT p FROM Post p")
    List<Post> findAllPosts();

}
