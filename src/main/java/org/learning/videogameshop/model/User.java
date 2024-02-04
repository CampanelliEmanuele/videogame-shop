package org.learning.videogameshop.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // TODO: add registration date
//    private LocalDate registrationDate;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roleSet;

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchaseList;

    /* AUXILIARY METHODS */

    public boolean hasRole(String role) throws IllegalArgumentException {
        if (!role.equals("ADMIN") && !role.equals("USER")) {
            throw new IllegalArgumentException("User.hasRole() method has invalid String in input.");
        }
        for (Role r : roleSet) {
            if (r.getName().equals(role)) {
                return true;
            }
        }
        return false;
    }

    /* GETTERS AND SETTERS */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }
}
