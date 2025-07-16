package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.service.IPasswordEncoderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordEncoderService implements IPasswordEncoderService {

    private final PasswordEncoder encoder;

    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}