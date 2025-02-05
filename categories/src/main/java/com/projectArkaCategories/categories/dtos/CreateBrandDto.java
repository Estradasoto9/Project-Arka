package com.projectArkaCategories.categories.dtos;

import com.projectArkaCategories.categories.enums.Countries;
import jakarta.validation.constraints.NotBlank;

public class CreateBrandDto {

    @NotBlank()
    private  String name;

    @NotBlank()
    private String description;

    @NotBlank()
    private String logo;

    @NotBlank()
    private Countries countryOrigin;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Countries getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(Countries countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

}

