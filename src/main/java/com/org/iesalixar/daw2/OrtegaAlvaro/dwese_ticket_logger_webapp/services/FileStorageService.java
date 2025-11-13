package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    @Value("${UPLOAD_PATH}")
    private String uploadPath;

    /**
     * Saves an uploaded file and returns its unique name.
     */
    public String saveFile(MultipartFile file) {
        try {
            String fileExtension = getFileExtension(file.getOriginalFilename());
            String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

            Path filePath = Paths.get(uploadPath + File.separator + uniqueFileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            logger.info("File {} saved successfully at {}", uniqueFileName, filePath);
            return uniqueFileName;
        } catch (IOException e) {
            logger.error("Error saving file: {}", e.getMessage());
            return null;
        }
    }

    public void deleteFile(String fileName) {
        try {
            Path filePath = Paths.get(uploadPath, fileName);
            Files.deleteIfExists(filePath);
            logger.info("File {} deleted successfully.", fileName);
        } catch (IOException e) {
            logger.error("Error deleting file {}: {}", fileName, e.getMessage());
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf('.') + 1);
        } else {
            return "";
        }
    }
}

