package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.entity.MyLinkEntity;
import ru.itpark.entity.MyMultimediaEntity;

import java.util.List;


public interface MyLinkRepository extends JpaRepository<MyLinkEntity, Integer> {
    List<MyLinkEntity> findAllById(int id);
}
