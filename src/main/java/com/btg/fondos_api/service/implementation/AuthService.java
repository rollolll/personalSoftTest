package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.config.jwt.JwtService;
import com.btg.fondos_api.dto.AuthResponse;
import com.btg.fondos_api.dto.LoginRequest;
import com.btg.fondos_api.dto.RegistrarRequest;
import com.btg.fondos_api.dto.DetallesAutUsuariosDto;
import com.btg.fondos_api.exception.RecursoYaExistenteException;
import com.btg.fondos_api.persistence.model.UsuarioModel;
import com.btg.fondos_api.persistence.repository.UsuarioRepository;
import com.btg.fondos_api.service.IAuthService;
import com.btg.fondos_api.service.IPasswordEncoderService;
import com.btg.fondos_api.utilities.ConstantesAplicacion;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final IPasswordEncoderService iPasswordEncoderService;


    @Override
    public AuthResponse login(LoginRequest request) {
            authenticate(request.getUsername(), request.getPassword());
            UsuarioModel user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = generateTokenForUser(user);
            return AuthResponse.builder()
                    .token(token)
                    .username(request.getUsername())
                    .rol(user.getRoles())
                    .build();
    }

    @Override
    public AuthResponse register(RegistrarRequest request) {
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RecursoYaExistenteException(ConstantesAplicacion.ERROR_USUARIO_EXISTENTE);
        }
        String encodedPassword = iPasswordEncoderService.encode(request.getPassword());
        UsuarioModel user = UsuarioModel.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .roles(request.getRoles())
                .state(true)
                .build();
        usuarioRepository.save(user);
        return AuthResponse.builder()
                .username(request.getUsername())
                .build();
    }

    private String generateTokenForUser(UsuarioModel user) {
        UserDetails userDetails = new DetallesAutUsuariosDto(user);
        return jwtService.getToken(userDetails);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
