package com.projectArkaCategories.categories.Controllers;

import com.projectArkaCategories.categories.dtos.CreateCategoryDto;
import com.projectArkaCategories.categories.dtos.ProductWebDto;
import com.projectArkaCategories.categories.dtos.UpdateCategoryDto;
import com.projectArkaCategories.categories.entities.Category;
import com.projectArkaCategories.categories.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; // import all annotation
import jakarta.validation.Valid;

import java.util.List;

@RestController // this annotation for the controller to work correctly
public class CategoryController {

    @Autowired
    CategoryService service;

    @RequestMapping(value = "/category/")
    public List<Category> all(){
        return this.service.getAll();
    }

    @GetMapping(value = "/category/{id}")
    public Category get(@PathVariable(value = "id") Long id){
        System.out.println("Category " + id );
        return this.service.get(id);
    }

    @GetMapping(value = "/category/search")
    public List<Category> search(@RequestParam(value = "name") String name){
        return  service.getByName(name);
    }

    @GetMapping(value = "/category/products")
    public List<ProductWebDto> getProduct(){
        return service.getProducts();
    }

    @PostMapping(value = "/category")
    public Category create(@Valid @RequestBody() CreateCategoryDto category){
        return this.service.create(category);
    }

    @PatchMapping(value = "/category/{id}")
    public Category update(@PathVariable(value = "id") Long id, @Valid @RequestBody() UpdateCategoryDto category){
        return this.service.update(id, category);
    }

    @DeleteMapping(value = "/category/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        return  this.service.delete(id);
    }
}
