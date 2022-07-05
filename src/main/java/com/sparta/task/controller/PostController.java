package com.sparta.task.controller;

import com.sparta.task.domain.Post;
import com.sparta.task.domain.PostDto;
import com.sparta.task.domain.PostRepository;
import com.sparta.task.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    // 전체 게시글 목록 조회
    @GetMapping("/api/posts")
    public List<Post> postList() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 생성
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostDto postDto) {
        Post post = new Post(postDto);
        return postRepository.save(post);
    }

    // 게시글 조회 API - 상세 조회 느낌인건가?
    @GetMapping("/api/posts/{id}")
    public Post postDetail(@PathVariable Long id) {
        return postRepository.findById(id).get();
    }

    // 게시글 수정
    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        return postService.update(id, postDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }
}
