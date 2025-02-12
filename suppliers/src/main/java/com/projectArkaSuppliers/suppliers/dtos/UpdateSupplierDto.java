package com.projectArkaSuppliers.suppliers.dtos;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class UpdateSupplierDto {
    private String name;
    private String description;
    private String address;
    private String phone;

    @Email
    private String email;

    @URL
    private String webSite;

    private Boolean isActive;
}
