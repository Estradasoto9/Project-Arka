package com.projectArkaCategories.categories.services;

import com.projectArkaCategories.categories.dtos.CreateCategoryDto;
import com.projectArkaCategories.categories.dtos.ProductWebDto;
import com.projectArkaCategories.categories.dtos.UpdateCategoryDto;
import com.projectArkaCategories.categories.entities.Category;
import com.projectArkaCategories.categories.errors.CategoryNotFound;
import com.projectArkaCategories.categories.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RestTemplate restTemplate;

    public Category create(CreateCategoryDto categoryDto){
        Category newCategory = new Category();
        newCategory.setName(categoryDto.getName());
        newCategory.setActiveSince(LocalDateTime.now());
        newCategory.setDescription(categoryDto.getDescription());
        newCategory.setImage(categoryDto.getImage());
        return this.categoryRepository.save(newCategory);
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category get(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFound(id));
    }

    public List<Category> getByName(String name){
        return categoryRepository.findByName(name);
    }

    public Category update(Long id, UpdateCategoryDto categoryDto){

        Category toUdpateCategory = this.get(id);

        if (categoryDto.getName() != null)
            toUdpateCategory.setName(categoryDto.getName() );
        if (categoryDto.getImage() != null)
            toUdpateCategory.setImage(categoryDto.getImage());
        if (categoryDto.getDescription() != null)
            toUdpateCategory.setDescription(categoryDto.getDescription());

        return categoryRepository.save(toUdpateCategory);
    }

    public  String delete(Long id){
        Category toDeleteCategory = this.get(id);
        categoryRepository.deleteById(id);
        return "Se ha eliminado una categoria";
    }

    public List<ProductWebDto> getProducts(){
        return List.of(
                restTemplate.getForObject("https://my-json-server.typicode.com/alejjandrotr/JavaDevOps2025/products", ProductWebDto[].class));
    }


}
