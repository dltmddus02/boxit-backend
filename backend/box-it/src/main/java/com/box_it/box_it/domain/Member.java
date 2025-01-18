package com.box_it.box_it.domain;

import java.time.LocalDateTime;

public class Member {
    private Long id;
    private String name;
    private String nickname;
    private String profileImageUrl;
    private String intro;
    private LocalDateTime joinDate;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
