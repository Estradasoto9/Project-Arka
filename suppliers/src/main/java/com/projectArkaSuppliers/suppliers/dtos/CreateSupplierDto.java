package com.projectArkaSuppliers.suppliers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private boolean active;

    @NotNull()
    private SupplierContactDto contact;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public SupplierContactDto getContact() {
        return contact;
    }

    public void setContact(SupplierContactDto contact) {
        this.contact = contact;
    }

}
