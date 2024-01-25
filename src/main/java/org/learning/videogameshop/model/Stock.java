package org.learning.videogameshop.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate purchaseDate;

    private Integer quantity;

    private String supplierName;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "videogame_id")
    private Videogame purchasedVideogame;

}
