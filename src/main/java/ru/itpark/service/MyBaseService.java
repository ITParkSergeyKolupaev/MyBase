package ru.itpark.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.dto.MyLinkEntityDto;
import ru.itpark.dto.MyMultimediaEntityDto;
import ru.itpark.dto.MyTextEntityDto;
import ru.itpark.entity.MyLinkEntity;
import ru.itpark.entity.MyMultimediaEntity;
import ru.itpark.entity.MyTextEntity;
import ru.itpark.exception.*;
import ru.itpark.repository.MyLinkRepository;
import ru.itpark.repository.MyMultimediaRepository;
import ru.itpark.repository.MyTextRepository;

import java.net.URI;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@Service
public class MyBaseService {
    private final MyTextRepository repositoryMyText;
    private final MyMultimediaRepository repositoryMultimedia;
    private final MyLinkRepository repositoryLink;
    private final Path uploadPath;


    public MyBaseService(MyTextRepository repositoryMyText, MyMultimediaRepository repositoryMultimedia,
                         MyLinkRepository repositoryLink,
                         @Value("${spring.resources.static-locations}") String uploadPath) {
        this.repositoryMyText = repositoryMyText;
        this.repositoryMultimedia = repositoryMultimedia;
        this.repositoryLink = repositoryLink;
        this.uploadPath = Path.of(URI.create(uploadPath)).resolve("media");
        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////----Text
    public List<MyTextEntity> getAllText() {
        return repositoryMyText.findAll();
    }

    public MyTextEntity getByIdText(int id) {
        return repositoryMyText.findById(id).orElseThrow(TopicNotFoundException::new);
    }


    public MyTextEntity getByIdOrEmptyText(int id) {
        return repositoryMyText.findById(id).orElse(new MyTextEntity());
    }
    public void saveText(MyTextEntityDto itemText) {

        MyTextEntity entityText = getByIdOrEmptyText(itemText.getId());

        entityText.setId(itemText.getId());
        entityText.setName(itemText.getName());
        if (itemText.getName().length() == 0) {
            throw new NameIsNullException();
        }
        entityText.setText(itemText.getText());

        repositoryMyText.save(entityText);
    }
    public void removeByIdText(int id) {
        repositoryMyText.deleteById(id);
    }

    ////----Multimedia
    public List<MyMultimediaEntity> getAllMultimedia() {
        return repositoryMultimedia.findAll();
    }

    public MyMultimediaEntity getByIdMultimedia(int id) {
        return repositoryMultimedia.findById(id).orElseThrow(MultimediaNotFoundException::new);
    }

    public MyMultimediaEntity getByIdOrEmptyMultimedia(int id) {
        return repositoryMultimedia.findById(id).orElse(new MyMultimediaEntity());
    }
    public void saveMultimedia(MyMultimediaEntityDto item) {

        MyMultimediaEntity entityMultimedia = getByIdOrEmptyMultimedia(item.getId());

        entityMultimedia.setId(item.getId());
        entityMultimedia.setName(item.getName());
        if (item.getName().length() == 0) {
            throw new NameIsNullException();
        }
        MultipartFile file = item.getFile();   /////проверить
        if (!file.isEmpty() && file.getContentType() != null) {
            String ext;
            if (file.getContentType().equals(MediaType.IMAGE_PNG_VALUE)) {
                ext = ".png";
            } else if (file.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
                ext = ".jpg";
            } else {
                throw new UnsupportedFileContentTypeException();
            }

            String name = UUID.randomUUID().toString() + ext;

            try {
                file.transferTo(uploadPath.resolve(name));

                if (entityMultimedia.getPath() != null) {
                    Files.deleteIfExists(uploadPath.resolve(entityMultimedia.getPath()));
                }
            } catch (IOException e) {
                throw new UploadFileException(e);
            }
            entityMultimedia.setPath(name);
        }
        repositoryMultimedia.save(entityMultimedia);
    }
    public void removeByIdMultimedia(int id) {
        repositoryMultimedia.deleteById(id);
    }


    ////----Link
    public List<MyLinkEntity> getAllLink() {
        return repositoryLink.findAll();
    }

    public MyLinkEntity getByIdLink(int id) {
        return repositoryLink.findById(id).orElseThrow(LinkNotFoundException::new);
    }

    public MyLinkEntity getByIdOrEmptyLink(int id) {
        return repositoryLink.findById(id).orElse(new MyLinkEntity());
    }
    public void saveLink(MyLinkEntityDto itemLink) {
        MyLinkEntity entityLink = getByIdOrEmptyLink(itemLink.getId());

        entityLink.setId(itemLink.getId());
        entityLink.setName(itemLink.getName());
        if (itemLink.getName().length() == 0) {
            throw new NameIsNullException();
        }
        entityLink.setLink(itemLink.getLink());
        if (itemLink.getLink().length() == 0) {
            throw new NameIsNullException();
        }
        repositoryLink.save(entityLink);
    }
    public void removeByIdLink(int id) {
        repositoryLink.deleteById(id);
    }
}
