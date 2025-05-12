package org.example.crazybarbershop.services.interfaces;


import jakarta.servlet.http.Part;

public interface UploadImageService {

    void saveImageToStorage(Part part , String dir, String uniqueFileName);

}
