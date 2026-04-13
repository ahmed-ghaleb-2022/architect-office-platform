package com.architect.platform.auth.service;



import com.architect.platform.auth.dto.AuthResponse;
import com.architect.platform.auth.dto.CurrentUserResponse;
import com.architect.platform.auth.dto.LoginRequest;
import com.architect.platform.auth.dto.RegisterRequest;
import com.architect.platform.auth.security.CustomUserDetails;
import com.architect.platform.auth.security.JwtService;
import com.architect.platform.common.exception.BusinessException;
import com.architect.platform.user.entity.Role;
import com.architect.platform.user.entity.User;
import com.architect.platform.user.enums.RoleName;
import com.architect.platform.user.repository.RoleRepository;
import com.architect.platform.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email is already registered");
        }

        Role customerRole = roleRepository.findByName(RoleName.CUSTOMER)
                .orElseThrow(() -> new BusinessException("Default customer role was not found"));

        User user = new User();
        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setActive(true);
        user.getRoles().add(customerRole);

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser.getEmail());

        return new AuthResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()),
                token
        );
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail().trim().toLowerCase(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails.getUsername());

        return new AuthResponse(
                userDetails.getId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getUsername(),
                userDetails.getRoleNames(),
                token
        );
    }

    public CurrentUserResponse getCurrentUser(CustomUserDetails userDetails) {
        return new CurrentUserResponse(
                userDetails.getId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getUsername(),
                userDetails.getPhone(),
                userDetails.isEnabled(),
                userDetails.getRoleNames()
        );
    }
}
