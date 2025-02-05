package com.projectArkaCategories.categories.entities;

import com.projectArkaCategories.categories.enums.Countries;
import jakarta.persistence.*;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Countries getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(Countries countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    @Column()
    private String description;

    @Column()
    private String logo;

    @Enumerated(value = EnumType.STRING)
    private Countries countryOrigin;


}
