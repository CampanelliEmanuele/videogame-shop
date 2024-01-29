package org.learning.videogameshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "limits")
public class Limit {
    @Id
    private Integer id;

    @NotNull
    private Integer videogameLowerBound;

    @NotNull
    private Integer videogameMiddleBound;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideogameLowerBound() {
        return videogameLowerBound;
    }

    public void setVideogameLowerBound(Integer videogameLowerBound) {
        this.videogameLowerBound = videogameLowerBound;
    }

    public Integer getVideogameMiddleBound() {
        return videogameMiddleBound;
    }

    public void setVideogameMiddleBound(Integer videogameMiddleBound) {
        this.videogameMiddleBound = videogameMiddleBound;
    }
}
