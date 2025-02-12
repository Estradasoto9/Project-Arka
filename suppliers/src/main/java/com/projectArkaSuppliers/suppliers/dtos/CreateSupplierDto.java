package com.projectArkaSuppliers.suppliers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import java.util.Map;

import lombok.Data;
@Data
public class CreateSupplierDto {

    @NotBlank()
    private String name;

    @NotBlank()
    private String description;

    @NotBlank()
    private String address;

    @NotBlank()
    private String phone;

    @Email()
    private String email;

    @URL()
    private String webSite;

    @NotNull()
    private boolean isActive;


    public Map<String, String> getContact() {
        return Map.of(
                "phone", phone,
                "email", email,
                "webSite", webSite
        );
    }
}
