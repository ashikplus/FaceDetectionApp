package com.ashik.FaceDetectApp.config;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class OpenCVConfig {

    @PostConstruct
    public void loadOpenCV() {
        nu.pattern.OpenCV.loadLocally();
        System.out.println("OpenCV loaded successfully");
    }
}
