package com.finTechPoint.loanManageSystem.modules.users.service.impl;

import com.finTechPoint.loanManageSystem.config.JwtUtil;
import com.finTechPoint.loanManageSystem.modules.users.dto.LoginRequest;
import com.finTechPoint.loanManageSystem.modules.users.dto.UserInfoResponse;
import com.finTechPoint.loanManageSystem.modules.users.entity.User;
import com.finTechPoint.loanManageSystem.modules.users.repository.UserRepository;
import com.finTechPoint.loanManageSystem.modules.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserInfoResponse login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Base64 encode the input password
            String encodedInput = Base64.getEncoder().encodeToString(request.getPassword().getBytes());

            if (encodedInput.equals(user.getPassword())) {
                // Generate JWT
                String token = jwtUtil.generateToken(user.getUsername());

                // Build response
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

        return null; // or throw custom exception
    }

    @Override
    public UserInfoResponse getUserInfo(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return null;
        }

        User user = userOpt.get();
        return new UserInfoResponse(
                user.getFullName(),
                user.getRole(),
                user.getDepartment(),
                user.getOffice(),
                user.getUserMail(),
                null // No token for this one
        );
    }
}
