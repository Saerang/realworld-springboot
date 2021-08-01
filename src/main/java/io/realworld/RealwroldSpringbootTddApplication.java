package io.realworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
public class RealwroldSpringbootTddApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealwroldSpringbootTddApplication.class, args);
    }

}
