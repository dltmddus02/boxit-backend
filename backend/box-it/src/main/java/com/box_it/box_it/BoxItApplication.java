package com.box_it.box_it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoxItApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoxItApplication.class, args);
    }

}
