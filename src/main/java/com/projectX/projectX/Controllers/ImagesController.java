package com.projectX.projectX.Controllers;

import com.projectX.projectX.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
public class ImagesController {

    private ImageService imageService;

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
}
