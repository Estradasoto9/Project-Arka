package com.projectArkaCategories.categories.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCategoryDto {

    @NotNull()
    @NotBlank()
    String name;

    @NotBlank(message = "La descripcion no debe estar en blanco")
    String description;

    @NotBlank()
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

    public CreateCategoryDto(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
