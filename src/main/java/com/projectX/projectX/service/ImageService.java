package com.projectX.projectX.service;

import com.projectX.projectX.domain.Image;
import com.projectX.projectX.repository.ImageRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static String IMAGE_DIR_ROOT = "src/main/webapp/resources/static/images";

    private final ImageRepository imageRepository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public ImageService(ImageRepository imageRepository, ResourceLoader resourceLoader) {
        this.imageRepository = imageRepository;
        this.resourceLoader = resourceLoader;
        try {
            FileUtils.cleanDirectory(new File(IMAGE_DIR_ROOT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Resource getImage(String name)
    {
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
            if(!file.getContentType().split("/")[0].equals("image"))
                return null;
            Image image = new Image("temporaryName");
            imageRepository.save(image);
            String[] dirs = file.getOriginalFilename().split("\\.");    //could use ContentType, but for jpg returns jpeg :/
            image.setName("partyImage("+image.getId()+")."+dirs[dirs.length-1]);
            imageRepository.save(image);
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
}
