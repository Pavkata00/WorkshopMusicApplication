package com.workshop.demo.service.impl;

import com.workshop.demo.model.view.AlbumViewModel;
import com.workshop.demo.repository.AlbumRepository;
import com.workshop.demo.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AlbumViewModel> findAllAlbums() {
        return this.albumRepository.findAll().stream().
                map(albumEntity -> this.modelMapper.map(albumEntity,AlbumViewModel.class)).collect(Collectors.toList());
    }
}
