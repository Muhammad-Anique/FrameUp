package com.example.FrameUpServer.Model.Images;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(MultipartFile file) throws Exception{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            Image image = new Image(fileName,file.getContentType(),file.getBytes());
            return imageRepository.save(image);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }

    }

    @Override
    public Image getImage(String fileId) throws Exception{
        return imageRepository
                .findById(fileId).orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }
}
