package com.hospital.security;

import com.hospital.model.Activity;
import com.hospital.model.HospitalUser;
import com.hospital.repository.ActivityRepository;
import com.hospital.repository.HospitalUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserService implements UserDetailsService {

    private final HospitalUserRepository hospitalUserRepository;
    private final ActivityRepository activityRepository;

    public UserService(HospitalUserRepository hospitalUserRepository, ActivityRepository activityRepository) {
        this.hospitalUserRepository = hospitalUserRepository;
        this.activityRepository = activityRepository;
    }

    public UserDetails loadUserByUsername(String userName){
        final HospitalUser user = hospitalUserRepository.findUserByLogin(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " does not exist");
        }
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().getRole()));
        activityRepository.save(new Activity(user.getLogin()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
    }
}
