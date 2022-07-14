package com.sparta.task.service;

import com.sparta.task.domain.Post;
import com.sparta.task.dto.PostDto;
import com.sparta.task.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional  // 이게 DB에 진짜 반영이 돼야한다고 말해주는 어노테이션
    public Long update(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        post.update(postDto);
        return post.getId();
    }

}
