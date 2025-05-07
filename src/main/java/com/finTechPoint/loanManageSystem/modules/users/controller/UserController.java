package com.finTechPoint.loanManageSystem.modules.users.controller;

import com.finTechPoint.loanManageSystem.modules.users.dto.LoginRequest;
import com.finTechPoint.loanManageSystem.modules.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean success = userService.login(loginRequest);
        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
