package org.learning.videogameshop.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "videogames")
public class Videogame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name must not be blank")
//    @Size(min = 2, max = 40, message = "Name size must be from 2 to 40 characters")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Video game must have a price")
//    @Size(min = 2, max = 40, message = "Video game price must be at least 1")
    @Column(nullable = false)
    private BigDecimal price;
    private String description;
    @Lob
    private String photo;

    @ManyToMany
    @JoinTable(name = "videogame_type",
            joinColumns = @JoinColumn(name = "videogame_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private List<Type> typeList;

    @OneToMany(mappedBy = "videogame")
    private List<Purchase> purchaseList;

    @OneToMany(mappedBy = "stockedVideogame", orphanRemoval = true)
    private List<Stock> stockList;

    /* AUXILIARY METHODS */

//    public boolean isDeletable() {
//        return stockList.isEmpty() && purchaseList.isEmpty();
//    }

    public Integer getPurchasedCopies() {
        Integer purchasedCopies = 0;
        for (Purchase purchase : purchaseList) {
            if (purchase.getVideogame().getId() == id)
                purchasedCopies -= purchase.getQuantity();
        }
        return purchasedCopies;
    }

    public Integer getStockedCopies() {
        Integer stockedCopies = 0;
        for (Stock stock : stockList) {
            if (stock.getStockedVideogame().getId() == id)
                stockedCopies += stock.getQuantity();
        }
        return stockedCopies;
    }

    public Integer getAvailableCopies() {
        Integer availableCopies = 0;
        for (Stock stock : stockList) {
            if (stock.getStockedVideogame().getId() == id)
                availableCopies += stock.getQuantity();
        }
        for (Purchase purchase : purchaseList) {
            if (purchase.getVideogame().getId() == id)
                availableCopies -= purchase.getQuantity();
        }
        return availableCopies;
    }

    /* GETTER AND SETTER */

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }
}
