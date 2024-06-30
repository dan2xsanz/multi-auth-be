package com.multiauth.multiauthapplication.common.image;

import java.io.IOException;

public interface ImageService {

    String generateFileName();

    void uploadImage(Object id, String fileDirectory, String fileName, String base64Image) throws IOException;

    void deleteUploadImage(Object id, String fileDirectory, String fileName) throws IOException;

    String getUploadImage(Object id, String fileDirectory, String fileName) throws IOException;

}
