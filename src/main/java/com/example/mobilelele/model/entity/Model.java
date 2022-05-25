package com.example.mobilelele.model.entity;

import com.example.mobilelele.model.entity.enums.Category;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String imageUrl;

    private int startYear;

    private Long endYear;
}
