package com.architect.platform.health;

import com.architect.platform.common.response.ApiResponse;
import com.architect.platform.common.response.ResponseBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public ApiResponse<HealthStatusResponse> getHealthStatus(){
        HealthStatusResponse data = new HealthStatusResponse("UP","architect-platform");

        return ResponseBuilder.success("Health check successful", data);

    }
}
