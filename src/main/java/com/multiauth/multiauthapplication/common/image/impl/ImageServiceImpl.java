package com.multiauth.multiauthapplication.common.image.impl;

import com.multiauth.multiauthapplication.common.constant.Constant;
import com.multiauth.multiauthapplication.common.image.ImageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;


@Service
public class ImageServiceImpl implements ImageService {


    @Override
    public void uploadImage(Object id, String fileDirectory, String fileName, String base64Image) throws IOException {
        try {
            // Remove the data:image/<image-type>;base64, prefix
            String imageBase64 = base64Image.replaceFirst("data:image/[^;]+;base64,", "");

            // Check if directory already exists
            java.nio.file.Path fullFileDirectory = Paths.get(fileDirectory, id.toString());
            if (!Files.exists(fullFileDirectory)) {
                Files.createDirectories(fullFileDirectory);
            }

            // Decode base64 and process the image as needed
            byte[] bytes = Base64.getDecoder().decode(imageBase64);
            String fullFileName = String.format("%s\\%s", fullFileDirectory, fileName);
            java.nio.file.Path path = Paths.get(fullFileName);
            Files.write(path, bytes);
        } catch (IOException o) {
            throw new IOException(o);
        }
    }

    @Override
    public String generateFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return String.format("%s_%s.%s",
                Constant.IMAGE,
                formattedDateTime,
                Constant.JPEG);
    }

    @Override
    public String getUploadImage(Object id, String fileDirectory, String fileName) throws IOException {
        try {
            java.nio.file.Path fullFileDirectory = Paths.get(fileDirectory, id.toString());
            String fullFileName = String.format("%s\\%s", fullFileDirectory, fileName);
            byte[] imageBytes = Files.readAllBytes(Paths.get(fullFileName));
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException o) {
            throw new IOException(o);
        }
    }

}
