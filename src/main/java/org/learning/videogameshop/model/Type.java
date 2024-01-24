package org.learning.videogameshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


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
}
