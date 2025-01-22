package com.box_it.box_it.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    @Setter
    private String nickname;
    @Setter
    private String profile;
    @Setter
    private String intro;

    private LocalDateTime created_at;
    private LocalDateTime modified_at;

}
