package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.models.GalleryImage;
import org.example.crazybarbershop.repository.interfaces.GalleryImageRepository;
import org.example.crazybarbershop.services.interfaces.GalleryImageService;

import java.util.List;

@AllArgsConstructor
public class GalleryImageServiceImpl implements GalleryImageService {

    private GalleryImageRepository galleryImageRepository;

    @Override
    public List<GalleryImage> getAllImage() {
        return galleryImageRepository.findAll();
    }
}
