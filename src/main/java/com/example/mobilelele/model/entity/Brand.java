package com.example.mobilelele.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    @Column(nullable = false)
    private String name;

}
