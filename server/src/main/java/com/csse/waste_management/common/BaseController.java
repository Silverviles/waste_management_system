package com.csse.waste_management.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController("/api")
public class BaseController {
    private static final Logger LOGGER = Logger.getLogger(BaseController.class.getName());

    @GetMapping("/test")
    public String testConnection() {
        LOGGER.info("Connection successful");
        return "Connection successful";
    }
}
