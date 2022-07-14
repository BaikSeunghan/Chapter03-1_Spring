package com.sparta.task.domain;

import com.sparta.task.base.Timestamped;
import com.sparta.task.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post extends Timestamped {

    //    제목, 작성자명, 비밀번호, 작성내용
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int checkNum;

    // Dto 생성자
    public Post(PostDto postDto) {
        this.title = postDto.getTitle();
        this.username = postDto.getUsername();
        this.contents = postDto.getContents();
        this.checkNum = postDto.getCheckNum();

    }

    // 수정
    public void update(PostDto postDto) {
        this.contents = postDto.getContents();
    }
}
