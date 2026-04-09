package com.architect.platform.auth.controller;




import com.architect.platform.auth.dto.AuthResponse;
import com.architect.platform.auth.dto.CurrentUserResponse;
import com.architect.platform.auth.dto.LoginRequest;
import com.architect.platform.auth.dto.RegisterRequest;
import com.architect.platform.auth.security.CustomUserDetails;
import com.architect.platform.auth.service.AuthService;
import com.architect.platform.common.response.ApiResponse;
import com.architect.platform.common.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseBuilder.success("User registered successfully", authService.register(request));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseBuilder.success("Login successful", authService.login(request));
    }

    @GetMapping("/me")
    public ApiResponse<CurrentUserResponse> getCurrentUser(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseBuilder.success("Current user retrieved successfully", authService.getCurrentUser(userDetails));
    }
}
