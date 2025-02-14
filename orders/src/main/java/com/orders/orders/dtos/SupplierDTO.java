package com.orders.orders.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SupplierDTO {

    private Long id;

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotBlank(message = "El correo electrónico del proveedor es obligatorio")
    @Email(message = "Formato de correo electrónico no válido")
    private String email;

    @NotBlank(message = "El número de contacto es obligatorio")
    @Pattern(regexp = "\\d{10,15}", message = "El número de contacto debe tener entre 10 y 15 dígitos")
    private String contactNumber;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
