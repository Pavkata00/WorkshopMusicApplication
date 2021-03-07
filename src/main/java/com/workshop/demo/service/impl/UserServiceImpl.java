package com.workshop.demo.service.impl;

import com.workshop.demo.model.entities.RoleEntity;
import com.workshop.demo.model.entities.UserEntity;
import com.workshop.demo.model.entities.enums.RoleEnum;
import com.workshop.demo.model.service.UserServiceModel;
import com.workshop.demo.repository.RoleRepository;
import com.workshop.demo.repository.UserRepository;
import com.workshop.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final MusicDBUserDetailsService musicDBUserDetailsService;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, MusicDBUserDetailsService musicDBUserDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.musicDBUserDetailsService = musicDBUserDetailsService;
    }

    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {

            RoleEntity adminRole = new RoleEntity();
            RoleEntity userRole = new RoleEntity();

            adminRole.setRole(RoleEnum.ADMIN);
            userRole.setRole(RoleEnum.USER);

            roleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setRoles(List.of(adminRole, userRole));

            UserEntity user = new UserEntity();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("12345"));
            user.setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, user));
        }
    }

    @Override
    public void registerAndLogin(UserServiceModel userServiceModel) {
        UserEntity userEntity = this.modelMapper.map(userServiceModel,UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        userEntity.getRoles().add(roleRepository.findByRole(RoleEnum.USER).orElse(null));

        userRepository.save(userEntity);

        UserDetails principal = musicDBUserDetailsService.loadUserByUsername(userEntity.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal,userEntity.getPassword(), principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
