package com.workshop.demo.service.impl;

import com.google.gson.Gson;
import com.workshop.demo.model.entities.ArtistEntity;
import com.workshop.demo.repository.ArtistRepository;
import com.workshop.demo.service.ArtistService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final Gson gson;
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(Gson gson, ArtistRepository artistRepository) {
        this.gson = gson;
        this.artistRepository = artistRepository;
    }

    @Override
    public void seedArtists() throws IOException {

        if (this.artistRepository.count() == 0) {

            String content = String.join("", Files.readAllLines(Path.of("src/main/resources/init/artists.json")));
            ArtistEntity[] artistEntities = this.gson.fromJson(content,ArtistEntity[].class);

            Arrays.stream(artistEntities).forEach(this.artistRepository::save);
        }
    }
}
