package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.GalleryImage;

import java.util.List;

public interface GalleryImageRepository {

    List<GalleryImage> findAll();

}
