package com.example.FrameUpServer.Model.Images;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image saveImage(MultipartFile file) throws Exception;

    Image getImage(String fileId) throws Exception;
}
