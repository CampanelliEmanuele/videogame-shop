package org.learning.videogameshop.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "videogames")
public class Videogame {
    @Id
    private int id;
    private String description;
    private String photo;
    private Double price;
}
