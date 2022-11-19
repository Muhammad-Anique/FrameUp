package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.Images.Image;
import com.example.FrameUpServer.Model.Images.ImageService;
import com.example.FrameUpServer.Model.Images.ResponseData;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.awt.*;
@RestController
public class ImageController {

    private ImageService imageService;

    public ImageController(ImageService imageService)
    {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception
    {
//        ResponseEntity<?> return type
        Image image = null;
        String downloadUrl = "";
        image = imageService.saveImage(file);
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                .path(image.getId())
                .toUriString();

        System.out.println(downloadUrl);

        ResponseData responseData = new ResponseData();
        responseData.setFileName(image.getFileName());
        responseData.setFileSize(file.getSize());
        responseData.setDownloadUrl(downloadUrl);
        responseData.setFileType(file.getContentType());

        System.out.println("l\n");
        System.out.println(responseData);
        System.out.println("l\n");


//        return ResponseEntity.status(HttpStatus.OK).body("Success");
        return  responseData;

    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception
    {
        Image image = null;
        image = imageService.getImage(fileId);
        System.out.println(image);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + image.getFileName()
                                + "\"").body(new ByteArrayResource(image.getData()));

    }
}
