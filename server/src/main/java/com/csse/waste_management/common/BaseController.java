package com.csse.waste_management.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @GetMapping("/test")
    public String testConnection() {
        return "Connection successful";
    }
}
