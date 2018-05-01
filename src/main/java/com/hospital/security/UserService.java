package com.hospital.security;

import com.hospital.model.HospitalUser;
import com.hospital.repository.HospitalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements UserDetailsService {

    private final HospitalUserRepository hospitalUserRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final HospitalUser user = hospitalUserRepository.findUserByLogin(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " does not exist");
        }
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
    }

}
