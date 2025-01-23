package com.box_it.box_it.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Date due;

    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

}
