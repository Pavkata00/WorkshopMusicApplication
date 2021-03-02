package com.workshop.demo.config;

import com.workshop.demo.service.impl.MusicDBUserDetailsService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MusicDBUserDetailsService musicDBUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(MusicDBUserDetailsService musicDBUserDetailsService, PasswordEncoder passwordEncoder) {
        this.musicDBUserDetailsService = musicDBUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(musicDBUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                        antMatchers("/js/**", "/css/**", "/img/**").permitAll().
                        antMatchers("/", "/users/login", "/users/register").permitAll().
                        antMatchers("/**").authenticated().
                and().
                        formLogin().
                        loginPage("/users/login").
                        usernameParameter("username").
                        passwordParameter("password").
                        defaultSuccessUrl("/home").
                        failureForwardUrl("/users/login-error");//todo
    }
}
