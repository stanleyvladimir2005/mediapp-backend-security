package com.mitocode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class MediappBackendSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediappBackendSecurityApplication.class, args);
    }

    @GetMapping("/user")
    public Principal getUser(Principal user) {
        return user;
    }
}