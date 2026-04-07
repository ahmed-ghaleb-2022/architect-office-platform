package com.architect.platform.health;

import com.architect.platform.common.exception.BusinessException;
import com.architect.platform.common.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthTestController {

    @GetMapping("/api/test/not-found")
    public String testNotFound() {
        throw new ResourceNotFoundException("Test resource was not found");
    }

    @GetMapping("/api/test/business-error")
    public String testBusinessError() {
        throw new BusinessException("Test business rule failed");
    }

}
