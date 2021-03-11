package com.workshop.demo.web;

import com.workshop.demo.model.view.AlbumViewModel;
import com.workshop.demo.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumRestController {

    //TODO : CHECK MUST BE CORRECT THIS WAY! LUCHO DID ALBUM REPOSITORY DIRECTLY!
    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    public AlbumRestController(AlbumService albumService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    private List<AlbumViewModel> findAll() {

        return this.albumService.findAllAlbums();

    }
}
