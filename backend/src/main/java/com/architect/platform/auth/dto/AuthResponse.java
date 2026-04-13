package com.architect.platform.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> roles;
    private String token;


}
