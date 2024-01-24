package org.learning.videogameshop.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.TypeRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Table(name = "videogames")
public class Videogame {

    @Autowired
    private VideogameRepository videogameRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name must not be blank")
    @Size(min = 2, max = 40, message = "Name size must be from 2 to 40 characters")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Video game must have a price")
    @Size(min = 2, max = 40, message = "Video game price must be at least 1")
    @Column(nullable = false)
    private Double price;
    private String description;
    @Lob
    private String photo;

    @OneToOne
    private Purchase purchase;

    @ManyToMany
    private List<Type> typeList;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public VideogameRepository getVideogameRepository() {
        return videogameRepository;
    }

    public void setVideogameRepository(VideogameRepository videogameRepository) {
        this.videogameRepository = videogameRepository;
    }

    public TypeRepository getTypeRepository() {
        return typeRepository;
    }

    public void setTypeRepository(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public PurchaseRepository getPurchaseRepository() {
        return purchaseRepository;
    }

    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
}
