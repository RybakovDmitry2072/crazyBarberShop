package org.example.crazybarbershop.services.impl;

import jakarta.servlet.http.Part;
import org.example.crazybarbershop.services.interfaces.UploadImageService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class UploadImageServiceImpl implements UploadImageService {

    @Override
    public void saveImageToStorage(Part part, String dir, String uniqueFileName) {
        try {
            Path uploadPath = Paths.get(dir + File.separator + uniqueFileName);

            // Проверка и создание директории, если она не существует
            Path directoryPath = Paths.get(dir);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            Files.copy(part.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException("Error saving file", e);
        }
    }
}
