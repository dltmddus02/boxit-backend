package com.box_it.box_it.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    private boolean enabled = true;

    private String nickname;

    private String profileImageUrl;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
    private String intro;

    private LocalDateTime joinDate;

    public Member() {
    }

//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public String getProfileImageUrl() {
//        return profileImageUrl;
//    }
//
//    public String getIntro() {
//        return intro;
//    }
//
//    public LocalDateTime getJoinDate() {
//        return joinDate;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public void setProfileImageUrl(String profileImageUrl) {
//        this.profileImageUrl = profileImageUrl;
//    }
//
//    public void setIntro(String intro) {
//        this.intro = intro;
//    }
//
//    public void setJoinDate(LocalDateTime joinDate) {
//        this.joinDate = joinDate;
//    }
}
