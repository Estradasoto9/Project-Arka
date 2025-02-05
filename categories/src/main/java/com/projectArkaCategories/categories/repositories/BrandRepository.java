package com.projectArkaCategories.categories.repositories;

import com.projectArkaCategories.categories.entities.Brand;
import com.projectArkaCategories.categories.enums.Countries;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Page<Brand> findAll(Pageable pageable);
    Page<Brand> findByCountryOrigin(Countries country, Pageable pageable);

}
