package org.jwt.auth.security.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jwt.auth.security.configuration.CustomTokenProvider;
import org.jwt.auth.security.model.CustomUserDetails;
import org.jwt.auth.security.model.LoginRequest;
import org.jwt.auth.security.model.LoginResponse;
import org.jwt.auth.user.entity.ApplicationRole;
import org.jwt.auth.user.entity.ApplicationUser;
import org.jwt.auth.user.entity.UserLoginDetails;
import org.jwt.auth.user.repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;



    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        UserLoginDetails userLoginDetails = userRepo.findByEmailId(userNameOrEmail).
                orElseThrow(()->new UsernameNotFoundException("User not found with username or email : " + userNameOrEmail));
        return new CustomUserDetails(userLoginDetails, this.getAuthorities(userLoginDetails.getApplicationRoles()));
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Set<ApplicationRole> applicationRoles) {
        Set<SimpleGrantedAuthority> set = new HashSet<>();
        return applicationRoles.stream().map(role -> {
            set.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            return set;
        }).findFirst().get();
    }
//Note : we can'n write like this as it creates cyclic dependancy problem
    /*public LoginResponse getToken(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserNameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return LoginResponse.builder().accessToken(customTokenProvider.generateToken(authentication)).build();
    }*/
}
