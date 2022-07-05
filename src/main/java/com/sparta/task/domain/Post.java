package com.sparta.task.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped {

    //    제목, 작성자명, 비밀번호, 작성내용
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int checkNum;

    // Dto 생성자
    public Post(PostDto postDto) {
        this.title = postDto.getTitle();
        this.name = postDto.getName();
        this.contents = postDto.getContents();
        this.checkNum = postDto.getCheckNum();

    }

    // 수정
    public void update(PostDto postDto) {
        this.contents = postDto.getContents();
    }
}
