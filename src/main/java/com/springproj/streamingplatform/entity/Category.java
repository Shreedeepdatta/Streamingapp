package com.springproj.streamingplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    private int id;
    private String title;
    @OneToMany(mappedBy = "category")
    private List<Video> videos=new ArrayList<>();
}
