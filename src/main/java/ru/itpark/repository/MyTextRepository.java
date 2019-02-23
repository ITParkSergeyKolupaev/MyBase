package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.entity.MyTextEntity;

import java.util.List;


public interface MyTextRepository extends JpaRepository<MyTextEntity, Integer> {
    List<MyTextEntity> findAllById(int id);
}
