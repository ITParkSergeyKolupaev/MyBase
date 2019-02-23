package ru.itpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itpark.entity.MyLinkEntity;
import ru.itpark.entity.MyMultimediaEntity;
import ru.itpark.entity.MyTextEntity;
import ru.itpark.repository.MyLinkRepository;
import ru.itpark.repository.MyMultimediaRepository;
import ru.itpark.repository.MyTextRepository;


import java.util.List;

@SpringBootApplication
public class MyBaseApplication {

    public static void main(String[] args) {

        var context = SpringApplication.run(MyBaseApplication.class, args);
        var repositoryText = context.getBean(MyTextRepository.class);
        var repositoryLink = context.getBean(MyLinkRepository.class);
        var repositoryMulti = context.getBean(MyMultimediaRepository.class);

        repositoryText.saveAll(List.of(new MyTextEntity(1,
                "Mockito","Mocking framework for unit tests")));
        repositoryLink.saveAll(List.of(new MyLinkEntity( 1, "google", "www.google.ru")));
        repositoryMulti.saveAll(List.of(new MyMultimediaEntity( 1, "Картинка - Привет","hi")));
    }
}

