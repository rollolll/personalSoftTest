package com.btg.fondos_api.service;

import com.btg.fondos_api.dto.AuthResponse;
import com.btg.fondos_api.dto.LoginRequest;
import com.btg.fondos_api.dto.RegistrarRequest;

public interface IAuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegistrarRequest request);
}
