package com.finTechPoint.loanManageSystem.modules.users.service;

import com.finTechPoint.loanManageSystem.modules.users.dto.LoginRequest;
import com.finTechPoint.loanManageSystem.modules.users.dto.UserInfoResponse;

public interface UserService {
    UserInfoResponse login(LoginRequest request);
    UserInfoResponse getUserInfo(String username);
}

