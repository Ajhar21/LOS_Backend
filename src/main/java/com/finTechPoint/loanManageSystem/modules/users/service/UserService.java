package com.finTechPoint.loanManageSystem.modules.users.service;

import com.finTechPoint.loanManageSystem.modules.users.dto.LoginRequest;
import com.finTechPoint.loanManageSystem.modules.users.dto.UserInfoResponse;

public interface UserService {
    
    /**
     * Handles user login by validating credentials.
     * 
     * @param request LoginRequest containing username and password
     * @return true if login is successful, false otherwise
     */
    boolean login(LoginRequest request);
    
    /**
     * Fetches detailed user information by user ID.
     *
     * @param userId the ID of the user
     * @return UserInfoResponse containing full name, role, department, office, and user mail
     */
    UserInfoResponse getUserInfo(String username);
}
