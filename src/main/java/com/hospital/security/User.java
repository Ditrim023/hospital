package com.hospital.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Nikita Krutoguz
 */
public class User extends org.springframework.security.core.userdetails.User{
    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
