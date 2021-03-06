package org.jwt.auth.security.Controller;

import lombok.RequiredArgsConstructor;
import org.jwt.auth.security.configuration.CustomTokenProvider;
import org.jwt.auth.security.model.LoginRequest;
import org.jwt.auth.security.model.LoginResponse;
import org.jwt.auth.security.service.CustomUserDetailService;
import org.jwt.auth.user.model.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomUserDetailService customUserDetailService;
    private final CustomTokenProvider customTokenProvider;
    private final AuthenticationManager authenticationManager;



    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserNameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(LoginResponse.builder().accessToken(customTokenProvider.generateToken(authentication)).build());
    }
}
