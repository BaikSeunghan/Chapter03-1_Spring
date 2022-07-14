package com.sparta.task.controller;

import com.sparta.task.domain.Comment;
import com.sparta.task.dto.CommentDto;
import com.sparta.task.repository.CommentRepository;
import com.sparta.task.security.UserDetailsImpl;
import com.sparta.task.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommentController {


    private final CommentRepository commentRepository;
    private final CommentService commentService;

    // 해당 게시글 댓글 목록 조회
    @GetMapping("/api/comments/{postId}")
    @ResponseBody
    public List<Comment> commentList(@PathVariable Long postId) {
        return commentService.getCommentList(postId);
    }

    // 댓글 생성
    @PostMapping("/api/commnets")
    public String createComment(@RequestBody CommentDto commentDto,
                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                BindingResult bindingResult) {
        // 비 로그인일 때
        if (userDetails == null) {
            bindingResult.reject("needLogin", "로그인이 필요한 기능입니다.");
        } else {
            // 댓글 내용란이 공란일 때
            if (bindingResult.hasErrors()) {
                return " ";
            }
        }

        // 댓글 작성
        Comment comment = new Comment(commentDto);
        commentRepository.save(comment);

        return "redirect: ";
    }

    // 댓글 수정
    @PutMapping("/api/comments/{id}")
    @ResponseBody
    public Long updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // - 로그인 토큰에 해당하는 사용자가 작성한 댓글만 수정 가능하도록 하기
        // - API를 호출한 경우 기존 댓글의 내용을 새로 입력한 댓글 내용으로 바꾸기
        return 1L;
    }


    // 댓글 삭제
    // - 로그인 토큰에 해당하는 사용자가 작성한 댓글만 삭제 가능하도록 하기
    @DeleteMapping("/api/comments/{id}")
    @ResponseBody
    public Long deletePost(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
