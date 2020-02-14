package com.wl.kmail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("com.wl.kmail.dao")
public class KmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(KmailApplication.class, args);
    }

}
