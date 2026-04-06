package com.architect.platform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("api/health")
    public String checkStatus(){
        return "status : UP";
    }
}
