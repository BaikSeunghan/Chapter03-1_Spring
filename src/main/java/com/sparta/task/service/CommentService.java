package com.sparta.task.service;

import com.sparta.task.domain.Comment;
import com.sparta.task.dto.CommentDto;
import com.sparta.task.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // 게시글 ID로 등록된 댓글 조회
    public List<Comment> getCommentList(Long postId) {
        return commentRepository.findAllByBlogIdOrderByCreatedAtDesc(postId);
    }

    @Transactional
    public Long update(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        return comment.update(commentDto);
    }

}
