package com.box_it.box_it.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime start_at;
    @Column(nullable = false)
    private LocalDateTime finish_at;

    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Goal goal;

}
