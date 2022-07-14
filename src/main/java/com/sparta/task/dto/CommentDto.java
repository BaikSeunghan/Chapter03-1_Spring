package com.sparta.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {


    @NotBlank(message = "댓글 내용을 입력해주세요")
    private String comment;

    @Column(nullable = false)
    private Long postId;
}
