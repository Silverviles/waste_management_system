package com.csse.waste_management.common;

import com.csse.waste_management.dto.BasicJsonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {
    protected static final Logger LOGGER = Logger.getLogger(TestController.class.getName());

    @PostMapping("/status")
    public ResponseEntity<BasicJsonResponse> test() {
        LOGGER.info("Test controller is working");
        return ResponseEntity.ok(new BasicJsonResponse("Test controller is working", "success"));
    }
}
