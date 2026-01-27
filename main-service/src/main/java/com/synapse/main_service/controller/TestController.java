package com.synapse.main_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/ping")
    public String ping() {
        return "Main Service en el 8080: Operativo y sin fantasmas, bro 🚀";
    }
}
