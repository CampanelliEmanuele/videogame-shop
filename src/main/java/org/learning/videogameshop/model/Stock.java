package org.learning.videogameshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @PastOrPresent
    private LocalDate purchaseDate;

    @NotEmpty(message = "Supplier's name cannot be empty")
    private String supplierName;

    @Min(1)
    @NotNull(message = "Enter a valid price value")
    private Double price;

    @Min(1)
    @NotNull(message = "Enter a valid quantity value")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "videogame_id")
    private Videogame stockedVideogame;

    /* AUXILIARY METHODS */

    public Double getTotalCost() {
        return -1 * quantity * price;
    }

    /* GETTERS AND SETTERS */

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

    public Videogame getStockedVideogame() {
        return stockedVideogame;
    }

    public void setStockedVideogame(Videogame stockedVideogame) {
        this.stockedVideogame = stockedVideogame;
    }
}
