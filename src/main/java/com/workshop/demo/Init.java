package com.workshop.demo;

import com.workshop.demo.service.ArtistService;
import com.workshop.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Init implements CommandLineRunner {

    private final UserService userService;
    private final ArtistService artistService;

    public Init(UserService userService, ArtistService artistService) {
        this.userService = userService;
        this.artistService = artistService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.seedUsers();
        this.artistService.seedArtists();
    }
}
