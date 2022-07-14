package com.sparta.task.domain;

import com.sparta.task.base.Timestamped;
import com.sparta.task.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long postId;

    public Comment(CommentDto commentDto) {
        this.username = username;
        this.comment = commentDto.getComment();
        this.postId = commentDto.getPostId();
    }

    public long update(CommentDto commentDto) {
        this.comment = commentDto.getComment();
        this.postId = commentDto.getPostId();
        return id;
    }

}
