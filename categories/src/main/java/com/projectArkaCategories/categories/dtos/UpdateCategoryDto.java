package com.projectArkaCategories.categories.dtos;

import jakarta.validation.constraints.Size;


public class UpdateCategoryDto {
    String name;

    @Size(min = 0, max = 7)
    String description;

    String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UpdateCategoryDto(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
