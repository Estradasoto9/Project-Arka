package com.projectArkaSuppliers.suppliers.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column()
    private String address;

    @Column()
    private String phone;

    @Column()
    private String email;

    @Column()
    private String website;

    @Column()
    private boolean isActive;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column()
    private Date firstPurchase;

    @OneToOne(targetEntity = SupplierContact.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private SupplierContact contact;

    @OneToMany(mappedBy = "supplier")
    @JsonManagedReference
    private List<Store> stores;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "product_supplier",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private Set<Product> products = new HashSet<>();


    public void setIsActive(boolean b) {
    }
}
