package com.workshop.demo.service.impl;

import com.workshop.demo.model.entities.UserEntity;
import com.workshop.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MusicDBUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MusicDBUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByName(username).
                orElseThrow(() ->new UsernameNotFoundException("User with name " + username + " not found!"));

        return mapUser(userEntity);
    }

    private UserDetails mapUser(UserEntity userEntity) {
        List<GrantedAuthority> grantedAuthorities =
                userEntity.getRoles().stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRole().name())).collect(Collectors.toList());

        return new User(userEntity.getName(),userEntity.getPassword(),grantedAuthorities);
    }
}
