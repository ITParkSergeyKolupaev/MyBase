package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.entity.MyMultimediaEntity;
import java.util.List;


public interface MyMultimediaRepository extends JpaRepository<MyMultimediaEntity, Integer> {
    List<MyMultimediaEntity> findMyMultimediaEntitiesById(int id);
}
