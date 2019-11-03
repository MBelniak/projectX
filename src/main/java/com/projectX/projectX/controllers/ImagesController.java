package com.projectX.projectX.controllers;

import com.projectX.projectX.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;

@RestController
public class ImagesController {

    private final ImageService imageService;

    @Autowired
    public ImagesController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/images")
    public String addImage(@RequestParam("file") MultipartFile file)
    {
        try {
            return imageService.createImage(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/images/{filename}")
    public ResponseEntity<?> getRawImage(@PathVariable String filename)
    {
        try {
            Resource resource = imageService.getImage(filename);
            return ResponseEntity.ok()
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(filename)))
                    .body(new InputStreamResource(resource.getInputStream()));
        } catch (IOException e) {
            return ResponseEntity.badRequest()
                    .body("Cannot find "+filename+"=>"+e.getMessage());
        }
    }
  }
