package ru.itpark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "MyLinkEntity")
public class MyLinkEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;
    private String link;

}
