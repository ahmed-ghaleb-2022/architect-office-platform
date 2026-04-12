package com.architect.platform.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class CurrentUserResponse {


    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean active;
    private Set<String> roles;


}
