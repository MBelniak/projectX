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
import java.util.ArrayList;
import java.util.List;

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

    public void saveImage(Image image)
    {
        imageRepository.save(image);
    }
    public void createImage(MultipartFile file) throws IOException {
        if(!file.isEmpty())
        {
            String[] dir_names = file.getOriginalFilename().split("\\\\");
            String file_name = dir_names[dir_names.length - 1];
            Files.copy(file.getInputStream(), Paths.get(IMAGE_DIR_ROOT, file_name));
        }
    }
    public void deleteImage(String filename) throws IOException {
        final Image image = imageRepository.findByName(filename);
        imageRepository.delete(image);
        Files.deleteIfExists(Paths.get(IMAGE_DIR_ROOT, filename));
    }
}
