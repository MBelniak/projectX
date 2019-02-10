package com.projectX.projectX.service;

import com.projectX.projectX.domain.Image;
import com.projectX.projectX.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageService {

    private static String IMAGE_DIR_ROOT = "image_dir";

    private final ImageRepository imageRepository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public ImageService(ImageRepository imageRepository, ResourceLoader resourceLoader) {
        this.imageRepository = imageRepository;
        this.resourceLoader = resourceLoader;
    }

    public Resource getImage(String name)
    {
        return resourceLoader.getResource("file:"+IMAGE_DIR_ROOT+"/"+name);
    }
    public void createImage(MultipartFile file) throws IOException {
        if(!file.isEmpty())
        {
            Files.copy(file.getInputStream(), Paths.get(IMAGE_DIR_ROOT, file.getOriginalFilename()));
            imageRepository.save(new Image(file.getOriginalFilename()));
        }
    }
    public void deleteImage(String filename) throws IOException {
        final Image image = imageRepository.findByName(filename);
        imageRepository.delete(image);
        Files.deleteIfExists(Paths.get(IMAGE_DIR_ROOT, filename));
    }
}
