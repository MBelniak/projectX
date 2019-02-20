package com.projectX.projectX.service;

import com.projectX.projectX.domain.Image;
import com.projectX.projectX.repository.ImageRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class ImageService {

    private static final String IMAGE_DIR_ROOT = "C:\\Users\\rubik\\IdeaProjects\\projectX\\projectX\\images";

    private final ImageRepository imageRepository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public ImageService(ImageRepository imageRepository, ResourceLoader resourceLoader) {
        this.imageRepository = imageRepository;
        this.resourceLoader = resourceLoader;
    }

    public Resource getImage(String name){
        return resourceLoader.getResource("file:"+IMAGE_DIR_ROOT+"/"+name);
    }

    public Image getImageEntity(String name)
    {
        return imageRepository.findByName(name);
    }

    public void saveImage(Image image)
    {
        imageRepository.save(image);
    }
    public String createImage(MultipartFile file) throws IOException {
        if(!file.isEmpty())
        {
            try {
                if (!file.getContentType().split("/")[0].equals("image"))
                    return null;
            }
            catch (NullPointerException e)
            {
                return null;
            }
            Image image = new Image("temporaryName");
            imageRepository.save(image);
            image.setName("partyImage("+image.getId()+")."+file.getContentType().split("/")[1]);
            Files.copy(file.getInputStream(), Paths.get(IMAGE_DIR_ROOT, image.getName()), StandardCopyOption.REPLACE_EXISTING);
            return image.getName();
        }
        return null;
    }
    public void deleteImage(String filename) throws IOException {
        final Image image = imageRepository.findByName(filename);
        imageRepository.delete(image);
        Files.deleteIfExists(Paths.get(IMAGE_DIR_ROOT, filename));
    }
    @Bean
    CommandLineRunner setUp()
    {
        return (args)-> FileUtils.cleanDirectory(new File(IMAGE_DIR_ROOT));
    }
}
