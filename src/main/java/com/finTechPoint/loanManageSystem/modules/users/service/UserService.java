package com.finTechPoint.loanManageSystem.modules.users.service;

import com.finTechPoint.loanManageSystem.modules.users.dto.LoginRequest;

public interface UserService {
    boolean login(LoginRequest request);
}
