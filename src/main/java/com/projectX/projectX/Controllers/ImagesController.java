package com.projectX.projectX.Controllers;

import com.projectX.projectX.domain.Image;
import com.projectX.projectX.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@RestController
public class ImagesController {

    private ImageService imageService;

    @Autowired
    public ImagesController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/images")
    public void addImage(@RequestParam("file") MultipartFile file)
    {
        try {
            imageService.createImage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
