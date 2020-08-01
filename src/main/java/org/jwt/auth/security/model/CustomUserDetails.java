package org.jwt.auth.security.model;

import org.jwt.auth.user.entity.ApplicationUser;
import org.jwt.auth.user.entity.UserLoginDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;
    private Boolean isActive;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserLoginDetails applicationUser,
                             Collection<? extends GrantedAuthority> authorities) {
        this.email = applicationUser.getEmailId();
        this.password = applicationUser.getPassword();
        this.isActive = applicationUser.getIsActive();
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
