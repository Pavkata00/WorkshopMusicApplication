package com.workshop.demo.service;

import com.workshop.demo.model.view.AlbumViewModel;

import java.util.List;

public interface AlbumService {
    List<AlbumViewModel> findAllAlbums();
}
