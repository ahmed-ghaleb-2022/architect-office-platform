package com.architect.platform.health;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HealthStatusResponse {

    private final String status;
    private final String application;

}
