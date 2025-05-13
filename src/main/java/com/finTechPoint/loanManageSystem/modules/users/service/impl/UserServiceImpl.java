package com.finTechPoint.loanManageSystem.modules.users.service.impl;

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
/** 
    @Override
    public UserInfoResponse getUserInfo(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        UserInfoResponse response = new UserInfoResponse();
        response.setFullName(user.getFullName());
        response.setRole(user.getRole());
        response.setDepartment(user.getDepartment());
        response.setOffice(user.getOffice());
        response.setUserMail(user.getUserMail());

        return response;
    }
**/

    @Override
    public UserInfoResponse getUserInfo(String username) {
    Optional<User> userOpt = userRepository.findByUsername(username);

    if (userOpt.isEmpty()) {
        return null;
    }

    User user = userOpt.get();
    UserInfoResponse response = new UserInfoResponse();
    response.setFullName(user.getFullName());
    response.setRole(user.getRole());
    response.setDepartment(user.getDepartment());
    response.setOffice(user.getOffice());
    response.setUserMail(user.getUserMail());

    return response;
}

}

