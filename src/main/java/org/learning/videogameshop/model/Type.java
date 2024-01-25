package org.learning.videogameshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;


@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @ManyToMany(mappedBy = "typeList")
    private List<Videogame> videogameList;
    
    // GETTER AND SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Videogame> getVideogameList() {
        return videogameList;
    }

    public void setVideogameList(List<Videogame> videogameList) {
        this.videogameList = videogameList;
    }
}
