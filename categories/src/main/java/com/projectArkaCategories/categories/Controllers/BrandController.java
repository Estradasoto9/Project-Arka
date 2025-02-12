package com.projectArkaCategories.categories.Controllers;

import com.projectArkaCategories.categories.dtos.CreateBrandDto;
import com.projectArkaCategories.categories.entities.Brand;
import com.projectArkaCategories.categories.enums.Countries;
import com.projectArkaCategories.categories.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController

public class BrandController {

    @Autowired
    BrandRepository repository;

    @GetMapping("/brand")
    public Page<Brand> all(Pageable pageable){
        return repository.findAll(pageable);
    }

    @GetMapping("/brand/findByCountry")
    public Page<Brand> searchByCountry(Pageable pageable, @RequestParam Countries country){
        return repository.findByCountryOrigin(country, pageable);
    }

    @PostMapping("/brand")
    public Brand create(@RequestBody CreateBrandDto dto){
        Brand newBrand = new Brand();
        newBrand.setName(dto.getName());
        newBrand.setDescription(dto.getDescription());
        newBrand.setLogo(dto.getLogo());
        newBrand.setCountryOrigin(dto.getCountryOrigin());
        return repository.save(newBrand);
    }

}
