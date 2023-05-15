package com.application.nshi_home_work.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/ping")
    public HttpStatus healthcheck() {
        return HttpStatus.OK;
    }
}
