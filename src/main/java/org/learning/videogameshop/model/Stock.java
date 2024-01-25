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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Videogame getPurchasedVideogame() {
        return purchasedVideogame;
    }

    public void setPurchasedVideogame(Videogame purchasedVideogame) {
        this.purchasedVideogame = purchasedVideogame;
    }
}
