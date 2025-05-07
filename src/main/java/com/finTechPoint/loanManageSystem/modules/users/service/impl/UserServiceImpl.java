package com.finTechPoint.loanManageSystem.modules.users.service.impl;

import com.finTechPoint.loanManageSystem.modules.users.dto.LoginRequest;
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

    @Override
    public boolean login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Encode input password with Base64 before comparing
            String encodedInput = Base64.getEncoder().encodeToString(request.getPassword().getBytes());

            return encodedInput.equals(user.getPassword());
        }

        return false;
    }
}
