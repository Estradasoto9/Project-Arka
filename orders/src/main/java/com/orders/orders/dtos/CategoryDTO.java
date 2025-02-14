package com.orders.orders.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String name;
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    private String description;
}
