package com.finTechPoint.loanManageSystem.modules.users.controller;

import com.finTechPoint.loanManageSystem.config.JwtUtil;
import com.finTechPoint.loanManageSystem.modules.users.dto.LoginRequest;
import com.finTechPoint.loanManageSystem.modules.users.dto.UserInfoResponse;
import com.finTechPoint.loanManageSystem.modules.users.entity.User;
import com.finTechPoint.loanManageSystem.modules.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public UserInfoResponse login(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Decode Base64 password from DB
        String decodedPassword = new String(Base64.getDecoder().decode(user.getPassword()));

        // Validate user credentials
        if (!decodedPassword.equals(loginRequest.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        // Generate token
        String token = jwtUtil.generateToken(user.getUsername());

        // Return user info + token
        return new UserInfoResponse(
                user.getFullName(),
                user.getRole(),
                user.getDepartment(),
                user.getOffice(),
                user.getUserMail(),
                token
        );
    }
}
