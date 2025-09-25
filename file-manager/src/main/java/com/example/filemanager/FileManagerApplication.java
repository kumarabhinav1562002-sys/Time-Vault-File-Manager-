package com.example.filemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class FileManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileManagerApplication.class, args);
    }
}
